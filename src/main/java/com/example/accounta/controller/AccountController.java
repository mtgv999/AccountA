package com.example.accounta.controller;
import com.example.accounta.domain.Account;
import com.example.accounta.service.LockService;
import com.example.accounta.dto.AccountInfo;
import com.example.accounta.dto.CreateAccount;
import com.example.accounta.dto.DeleteAccount;
import com.example.accounta.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/create/account")
    public CreateAccount.Response createAccount
    (@RequestBody @Valid CreateAccount.Request request){
        return CreateAccount.Response.from
        (accountService.createAccount(request.getUserId(),
                request.getInitialBalance()));}
    @DeleteMapping("/delete/account")
    public DeleteAccount.Response deleteAccount
            (@RequestBody @Valid DeleteAccount.Request request){
        return DeleteAccount.Response.from
                (accountService.deleteAccount(request.getUserId(),
                        request.getAccountNumber()));}
    @GetMapping("/account/{id}")
    public Account getAccount
            (@PathVariable Long id){
        return accountService.getAccount(id);}
    @GetMapping("/account")
    public List<AccountInfo>getAccountByUserId
            (@RequestParam("user_id") Long userId) {
    return accountService.getAccountByUserId(userId)
            .stream().map(accountDto -> AccountInfo.builder()
            .accountNumber(accountDto.getAccountNumber())
                    .balance(accountDto.getBalance()).build())
            .collect(Collectors.toList());}
    }