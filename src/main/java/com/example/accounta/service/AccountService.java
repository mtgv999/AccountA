package com.example.accounta.service;
import com.example.accounta.domain.Account;
import com.example.accounta.domain.AccountUser;
import com.example.accounta.exception.AccountException;
import com.example.accounta.repository.AccountRepository;
import com.example.accounta.repository.AccountUserRepository;
import com.example.accounta.type.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.accounta.type.AccountStatus.IN_USE;

@Service @RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;
    @Transactional public Account createAccount
            (Long userId, Long initialBalance){
        AccountUser accountUser=accountUserRepository.findById(userId)
                .orElseThrow(()->new AccountException(ErrorCode.USER_NOT_FOUND));
        String newAccountNumber=accountRepository.findFirstByOOrderByIdDesc()
                .map(account -> (Integer.parseInt(account.getAccountNumber()))+1+"")
                .orElse("1000000000");
        Account savedAccount=accountRepository.save
                (Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(IN_USE)
                        .accountNumber(newAccountNumber)
                        .balance(initialBalance)
                        .registeredAt(LocalDateTime.now())
                        .build()); return savedAccount;}
    @Transactional
    public Account getAccount(Long id){
        if(id<0){throw new RuntimeException("Minus");}
        return accountRepository.findById(id).get();}}