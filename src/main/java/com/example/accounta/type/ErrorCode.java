package com.example.accounta.type;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter @AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("사용자 없음");
    private final String description;}