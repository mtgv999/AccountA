package com.example.accounta.dto;
import com.example.accounta.domain.Account;
import lombok.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class AccountDto {
    private Long userId;
    private String accountNumber;
    private Long balance;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    public static AccountDto fromEntity(Account account){
        return AccountDto.builder()
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .registeredAt(account.getRegisteredAt())
    .unregisteredAt(account.getUnRegisteredAt()).build();}}