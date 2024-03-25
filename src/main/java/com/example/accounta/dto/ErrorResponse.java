package com.example.accounta.dto;
import com.example.accounta.type.ErrorCode;
import lombok.*;
@Builder @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;}