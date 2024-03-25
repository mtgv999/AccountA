package com.example.accounta.domain;
import com.example.accounta.type.TransactionResultType;
import com.example.accounta.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Builder @NoArgsConstructor
@AllArgsConstructor @Setter @Getter
public class AccountUser extends BaseEntity{private String name;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private TransactionResultType transactionResultType;
    @ManyToOne
    private Account account;
    private Long amount;
    private Long balanceSnapshot;
    private String transactionId;
    private LocalDateTime transactedAt;}