package com.example.ztiproj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.example.ztiproj.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.example.ztiproj.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.example.ztiproj.repository.*.*(..))")
    private void forRepositoryPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forRepositoryPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info(() -> "@Before method name: " + methodName);
        Arrays.stream(joinPoint.getArgs())
                .forEach(arg -> logger.info(() -> "Method argument: " + arg));
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info(() -> "@AfterReturning method name: " + methodName);
        logger.info(() -> "@AfterReturning result: " + result);
    }
}
