package com.example.accounta.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
public class CreateAccount {
    @Setter @Getter
    @AllArgsConstructor
    public static class Request{
        @NotNull @Min(1)
        private Long userId;
        @NotNull @Min(1)
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
