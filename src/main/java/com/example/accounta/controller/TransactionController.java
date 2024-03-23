package com.example.accounta.controller;
import com.example.accounta.dto.UseBalance;
import com.example.accounta.exception.AccountException;
import com.example.accounta.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j @RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/transaction/use")
    public UseBalance.Response useBalance
            (@Valid @RequestBody UseBalance.Request request){
        try{return UseBalance.Response.from(
                transactionService.useBalance(request.getUserId(),
                        request.getAccountNumber(),request.getAmount()));}
        catch (AccountException e){
            log.error("밸런스 쓰는데 실패");
            transactionService.saveFailedUseTransaction
                    (request.getAccountNumber(),request.getAmount());throw e;}}}