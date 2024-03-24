package com.example.accounta.type;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter @AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("잘못된 요청"),
    USER_NOT_FOUND("사용자 없음"),
    ACCOUNT_NOT_FOUND("계좌 없음"),
    TRANSACTION_NOT_FOUND("해당 거래 없음"),
    AMOUNT_EXCEED_BALANCE("계좌 금액이 계좌 잔액보다 큼"),
    TRANSACTION_ACCOUNT_UN_MATCH
            ("이 거래는 해당 계좌에서 발생한 거래가 아님"),
    CANCEL_MUST_FULLY("부분 취소는 허용 안 됨."),
    TOO_OLD_ORDER_TO_CANCEL("1년이 지난 거래는 취소 못 함"),
    USER_ACCOUNT_UN_MATCH("사용자의 계좌의 소유가 다름"),
    ACCOUNT_ALREADY_UNREGISTERED("계좌가 이미 해지됨"),
    BALANCE_NOT_EMPTY("잔액이 있는 계좌는 해지할 수 없음."),
    MAX_ACCOUNT_PER_USED_10("사용할 최대 계좌: 10개");
    private final String description;}