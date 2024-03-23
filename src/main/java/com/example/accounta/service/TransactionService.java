package com.example.accounta.service;
import com.example.accounta.domain.Account;
import com.example.accounta.domain.AccountUser;
import com.example.accounta.domain.Transaction;
import com.example.accounta.dto.TransactionDto;
import com.example.accounta.exception.AccountException;
import com.example.accounta.repository.AccountRepository;
import com.example.accounta.repository.AccountUserRepository;
import com.example.accounta.repository.TransactionRepository;
import com.example.accounta.type.AccountStatus;
import com.example.accounta.type.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import static com.example.accounta.type.TransactionResultType.S;
import static com.example.accounta.type.TransactionType.USE;
@Slf4j @Service
@RequiredArgsConstructor
public class TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountUserRepository accountUserRepository;
    private final AccountRepository accountRepository;
    @Transactional public TransactionDto useBalance
            (Long userId, String accountNumber, Long amount){
        AccountUser user=accountUserRepository.findById(userId)
                .orElseThrow(()->new AccountException(ErrorCode.USER_NOT_FOUND));
        Account account=accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()->new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));
        validateUseBalance(user,account,amount);
        account.useBalance(amount);
        return TransactionDto.fromEntity(transactionRepository
                .save(Transaction.builder()
                .transactionType(USE).
                        transactionResultType(S)
                .account(account).amount(amount)
                .balanceSnapshot(account.getBalance())
                .transactionId(UUID.randomUUID().toString()
                        .replace("-",""))
                .transactedAt(LocalDateTime.now()).build()));}
    private void validateUseBalance
            (AccountUser user,Account account,Long amount){
        if(!Objects.equals(user.getId(),account.getAccountUser().getId())){
            throw new AccountException(ErrorCode.USER_ACCOUNT_UN_MATCH);}
        if(account.getAccountStatus()!= AccountStatus.IN_USE){
            throw new AccountException(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED);}
        if(account.getBalance()<amount){
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);}}

}