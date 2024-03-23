package com.example.accounta.domain;
import com.example.accounta.type.TransactionResultType;
import com.example.accounta.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
@Builder @Entity @NoArgsConstructor
@Getter @Setter @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transaction { @Id
    @GeneratedValue private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
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