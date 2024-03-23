package com.example.accounta.dto;
import com.example.accounta.type.TransactionResultType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
public class UseBalance {
    @Setter @Getter
    @AllArgsConstructor
    public static class Request{
        @NotNull @Min(1)
        private Long userId;
        @NotBlank @Size(min=10,max=10)
        private String accountNumber;
        @NotNull @Min(1)
        @Max(2_000_000_000)
        private Long amount;}
    @Builder @Setter @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private String accountNumber;
        private TransactionResultType transactionResult;
        private String transactionId;
        private Long amount;
        private LocalDateTime registeredAt;}}