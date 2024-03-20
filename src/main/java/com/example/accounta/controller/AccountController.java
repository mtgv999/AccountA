package com.example.accounta.controller;
import com.example.accounta.domain.Account;
import com.example.accounta.dto.CreateAccount;
import com.example.accounta.dto.DeleteAccount;
import com.example.accounta.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
        return accountService.getAccount(id);}}