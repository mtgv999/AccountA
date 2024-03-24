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
import com.example.accounta.type.TransactionResultType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import static com.example.accounta.type.TransactionResultType.F;
import static com.example.accounta.type.TransactionResultType.S;
import static com.example.accounta.type.TransactionType.CANCEL;
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
        return TransactionDto.fromEntity(saveAndGetTransaction(USE,S,account,amount));}
    private void validateUseBalance
            (AccountUser user,Account account,Long amount){
        if(!Objects.equals(user.getId(),account.getAccountUser().getId())){
            throw new AccountException(ErrorCode.USER_ACCOUNT_UN_MATCH);}
        if(account.getAccountStatus()!= AccountStatus.IN_USE){
            throw new AccountException(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED);}
        if(account.getBalance()<amount){
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);}}
    @Transactional
    public void saveFailedUseTransaction(String accountNumber, Long amount){
        Account account=accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()->new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));
        saveAndGetTransaction(USE,F,account,amount);}

    private Transaction saveAndGetTransaction
    (TransactionResultType transactionResultType, Account account,Long amount){
        return transactionRepository.save(Transaction.builder()
                        .transactionType(USE).
                        transactionResultType(transactionResultType)
                        .account(account).amount(amount)
                        .balanceSnapshot(account.getBalance())
                        .transactionId(UUID.randomUUID().toString()
                                .replace("-",""))
                        .transactedAt(LocalDateTime.now()).build());}

    public TransactionDto cancelBalance
            (String transactionId, String accountNumber, Long amount) {
        Transaction transaction=(Transaction)
        transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(()->new AccountException(ErrorCode.TRANSACTION_NOT_FOUND));
        Account account=accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()->new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));
        validateCancelBalance(transaction,account,amount);
        account.useBalance(amount);return TransactionDto.fromEntity
                (saveAndGetTransaction(CANCEL,S,account,amount));}
    private void validateCancelBalance
            (Transaction transaction,Account account,Long amount){
        if(!Objects.equals(transaction.getAccount()
                .getId(),account.getAccountUser().getId())){
            throw new AccountException(ErrorCode.TRANSACTION_ACCOUNT_UN_MATCH);}
        if(!Objects.equals(transaction.getAmount(),amount)){
            throw new AccountException(ErrorCode.CANCEL_MUST_FULLY);}
        if(transaction.getTransactedAt().isBefore(LocalDateTime.now().minusYears(1))){
            throw new AccountException(ErrorCode.TOO_OLD_ORDER_TO_CANCEL);}}
    @Transactional
    public void saveFailedCancelTransaction(String accountNumber, Long amount){
        Account account=accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()->new AccountException(ErrorCode.ACCOUNT_NOT_FOUND));
        saveAndGetTransaction(CANCEL,F,account,amount);}
    public TransactionDto queryTransaction(String transactionId){
        return TransactionDto.fromEntity((Transaction)
                transactionRepository.findByTransactionId(transactionId)
    .orElseThrow(()->new AccountException(ErrorCode.TRANSACTION_NOT_FOUND)));}
}