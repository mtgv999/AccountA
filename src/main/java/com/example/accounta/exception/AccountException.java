package com.example.accounta.exception;
import com.example.accounta.type.ErrorCode;
import lombok.*;
@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorMessage;
    public AccountException(ErrorCode errorCode){
        this.errorCode=errorCode;
        this.errorMessage=errorCode.getDescription();
    }
}