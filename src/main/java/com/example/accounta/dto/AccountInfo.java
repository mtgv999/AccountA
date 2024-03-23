package com.example.accounta.dto;
import lombok.*;
@Builder @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {
    private String accountNumber;
    private Long balance;}