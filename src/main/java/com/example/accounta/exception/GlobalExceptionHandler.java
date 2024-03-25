package com.example.accounta.exception;
import com.example.accounta.dto.ErrorResponse;
import com.example.accounta.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static com.example.accounta.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.accounta.type.ErrorCode.INVALID_REQUEST;
@Slf4j @RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountException.class)
    public ErrorResponse handleAccountException(AccountException e){
        log.error("{} is occurred",e.getErrorCode());
        return new ErrorResponse(e.getErrorCode(),e.getErrorMessage());}
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolationException
            (DataIntegrityViolationException e){
        log.error("DataIntegrityViolationException",e);
        return new ErrorResponse(INVALID_REQUEST,INVALID_REQUEST.getDescription());}
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e){
        log.error("Exception is occurred.",e);
        return new ErrorResponse(INTERNAL_SERVER_ERROR,
                INTERNAL_SERVER_ERROR.getDescription());}
}
