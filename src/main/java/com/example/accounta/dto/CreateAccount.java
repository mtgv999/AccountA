package com.example.accounta.dto;
import lombok.*;
import java.time.LocalDateTime;
public class CreateAccount {
    @Setter @Getter
    @AllArgsConstructor
    public static class Request{
        private Long userId;
        private Long initialBalance;}
    @Builder @Setter @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long userId;
        private String accountNumber;
        private LocalDateTime registerdAt;
    public static Response from(AccountDto accountDto){
        return Response.builder()
                .userId(accountDto.getUserId())
                .accountNumber(accountDto.getAccountNumber())
            .registerdAt(accountDto.getRegisteredAt()).build();}}}
