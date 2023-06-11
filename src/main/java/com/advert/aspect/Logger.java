package com.advert.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class Logger {

    @Before("execution(* com.advert.controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint){
        log.info("Calling {}.{}() with arguments: {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @Before("execution(* com.advert.service.*.*(..))")
    public void logBeforeService(JoinPoint joinPoint){
        log.info("Calling {}.{}() with arguments: {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

}