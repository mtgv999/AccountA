package com.example.accounta.controller;
import com.example.accounta.domain.Account;
import com.example.accounta.dto.CreateAccount;
import com.example.accounta.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/account")
    public CreateAccount.Response createAccount
            (@RequestBody @Valid CreateAccount.Request request){
        accountService.createAccount(request.getUserId(),
                request.getInitialBalance());
        return "success";}
    @GetMapping("/account/{id}")
    public Account getAccount
            (@PathVariable Long id){
        return accountService.getAccount(id);}}