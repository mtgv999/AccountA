package com.example.accounta.controller;
import com.example.accounta.dto.UseBalance;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j @RestController
@RequiredArgsConstructor
public class TransactionController {
    @PostMapping("/transaction/use")
    public UseBalance.Response useBalance
            (@Valid @RequestBody UseBalance.Request request){

    }
}
