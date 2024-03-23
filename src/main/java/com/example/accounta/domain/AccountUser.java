package com.example.accounta.domain;
import com.example.accounta.type.TransactionResultType;
import com.example.accounta.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
@Entity @Builder @NoArgsConstructor
@AllArgsConstructor @Setter @Getter
@EntityListeners(AuditingEntityListener.class)
public class AccountUser {@Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private TransactionResultType transactionResultType;
    @ManyToOne
    private Account account;
    private Long amount;
    private Long balanceSnapshot;
    private String transactionId;
    private LocalDateTime transactedAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;}