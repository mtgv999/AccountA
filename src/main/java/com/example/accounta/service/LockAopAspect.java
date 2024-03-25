package com.example.accounta.service;
import com.example.accounta.aop.AccountLockIdInterface;
import com.example.accounta.dto.CancelBalance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect @Component @Slf4j
@RequiredArgsConstructor
public class LockAopAspect{
    private final LockService lockService;
    @Around("@annotation(com.example.accounta.aop.AccountLock)&&args(request)")
    public Object aroundMethod(ProceedingJoinPoint pjp,
                               AccountLockIdInterface request)
            throws Throwable{//lock 취득 시도
        lockService.lock(request.getAccountNumber());
        try{return pjp.proceed();}finally {//lock 해제
            lockService.unlock(request.getAccountNumber());}}}