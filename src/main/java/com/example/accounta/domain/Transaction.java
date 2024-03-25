package com.example.accounta.domain;
import com.example.accounta.type.TransactionResultType;
import com.example.accounta.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Builder @Entity @NoArgsConstructor
@Getter @Setter @AllArgsConstructor
public class Transaction extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private TransactionResultType transactionResultType;
    @ManyToOne
    private Account account;
    private Long amount;
    private Long balanceSnapshot;
    private String transactionId;
    private LocalDateTime transactedAt;}