package com.example.accounta.domain;
import com.example.accounta.type.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Builder @Entity @NoArgsConstructor
@Getter @Setter @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account { @Id
    @GeneratedValue private Long id;
    @ManyToOne
    private AccountUser accountUser;
    private String accountNumber;//[2]
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private Long balance;//[3]
    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;}