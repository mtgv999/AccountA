package com.example.accounta.controller;
import com.example.accounta.aop.AccountLock;
import com.example.accounta.dto.CancelBalance;
import com.example.accounta.dto.QueryTransactionResponse;
import com.example.accounta.dto.UseBalance;
import com.example.accounta.exception.AccountException;
import com.example.accounta.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j @RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/transaction/use") @AccountLock
    public UseBalance.Response useBalance
            (@Valid @RequestBody UseBalance.Request request)
    throws InterruptedException{try{Thread.sleep(5000L);
            return UseBalance.Response.from(
                transactionService.useBalance(request.getUserId(),
                        request.getAccountNumber(),request.getAmount()));}
        catch (AccountException e){log.error("밸런스 쓰는데 실패");
            transactionService.saveFailedUseTransaction
                    (request.getAccountNumber(),request.getAmount());throw e;}}
    @PostMapping("/transaction/use")
    public CancelBalance.Response cancelBalance
            (@Valid @RequestBody CancelBalance.Request request){
        try{return CancelBalance.Response.from(
                transactionService.cancelBalance(request.getTransactionId(),
                        request.getAccountNumber(),request.getAmount()));}
        catch (AccountException e){log.error("밸런스 쓰는데 실패");
            transactionService.saveFailedUseTransaction
                    (request.getAccountNumber(),request.getAmount());throw e;}}
    @GetMapping("/transaction/{transactionId}")
    public QueryTransactionResponse queryTransaction
            (@PathVariable String transactionId){
        return QueryTransactionResponse.from
                (transactionService.queryTransaction(transactionId));}
}