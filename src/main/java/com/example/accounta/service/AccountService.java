package com.example.accounta.service;
import com.example.accounta.domain.Account;
import com.example.accounta.domain.AccountUser;
import com.example.accounta.dto.AccountDto;
import com.example.accounta.exception.AccountException;
import com.example.accounta.repository.AccountRepository;
import com.example.accounta.repository.AccountUserRepository;
import com.example.accounta.type.AccountStatus;
import com.example.accounta.type.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.example.accounta.type.AccountStatus.IN_USE;
import static com.example.accounta.type.ErrorCode.USER_NOT_FOUND;
@Service @RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;
    @Transactional public AccountDto createAccount
            (Long userId, Long initialBalance){
        AccountUser accountUser=accountUserRepository.findById(userId)
                .orElseThrow(()->new AccountException(USER_NOT_FOUND));
        String newAccountNumber=accountRepository.findFirstByOrderByIdDesc()
                .map(account -> (Integer.parseInt(account.getAccountNumber()))+1+"")
                .orElse("1000000000");
        return AccountDto.fromEntity(
                accountRepository.save(Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(IN_USE)
                        .accountNumber(newAccountNumber)
                        .balance(initialBalance)
                    .registeredAt(LocalDateTime.now()).build()));}
    private void validateCreateAccount(AccountUser accountUser){
        if(accountRepository.countByAccountUser(accountUser)==10){
            throw new AccountException(ErrorCode.MAX_ACCOUNT_PER_USED_10);}}
    @Transactional
    public Account getAccount(Long id){
        if(id<0){throw new RuntimeException("Minus");}
        return accountRepository.findById(id).get();}
    @Transactional
    public AccountDto deleteAccount(Long userId, String accountNumber){
        AccountUser accountUser=accountUserRepository.findById(userId)
                .orElseThrow(()->new AccountException(USER_NOT_FOUND));
        Account account=accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()->new AccountException(USER_NOT_FOUND));
        validateDeleteAccount(accountUser,account);
        account.setAccountStatus(AccountStatus.UNREGISTERED);
        account.setUnRegisteredAt(LocalDateTime.now());
        return AccountDto.fromEntity(account);}
    private void validateDeleteAccount(AccountUser accountUser, Account account){
        if(!Objects.equals(accountUser.getId(),account.getAccountUser().getId())){
            throw new AccountException(ErrorCode.USER_ACCOUNT_UN_MATCH);}
        if(account.getAccountStatus()==AccountStatus.UNREGISTERED){
            throw new AccountException(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED);}
        if(account.getBalance()>0){
            throw new AccountException(ErrorCode.BALANCE_NOT_EMPTY);}}
    @Transactional
    public List<AccountDto> getAccountByUserId(Long userId) {
    AccountUser accountUser=accountUserRepository.findById(userId)
            .orElseThrow(()->new AccountException(USER_NOT_FOUND));
    List<Account>accounts=accountRepository.findByAccountUser(accountUser);
    return accounts.stream().map(AccountDto::fromEntity)
            .collect(Collectors.toList());}
}