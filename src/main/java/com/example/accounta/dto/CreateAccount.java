package com.example.accounta.dto;
import lombok.*;

import java.time.LocalDateTime;

public class CreateAccount {
    @Setter @Getter
    public static class Request{
        private Long userId;
        private Long initialBalance;}
    @Builder @Setter @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long userId;
        private String accountNumber;
        private LocalDateTime registerdAt;}}
