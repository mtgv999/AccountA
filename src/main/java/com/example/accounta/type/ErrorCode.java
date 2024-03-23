package com.example.accounta.type;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter @AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("사용자 없음"),
    ACCOUNT_NOT_FOUND("사용자 없음"),
    AMOUNT_EXCEED_BALANCE("계좌가 없습니다."),
    USER_ACCOUNT_UN_MATCH("사용자의 계좌의 소유가 다름"),
    ACCOUNT_ALREADY_UNREGISTERED("계좌가 이미 해지됨"),
    BALANCE_NOT_EMPTY("잔액이 있는 계좌는 해지할 수 없습니다."),
    MAX_ACCOUNT_PER_USED_10("사용할 최대 계좌: 10개");
    private final String description;}