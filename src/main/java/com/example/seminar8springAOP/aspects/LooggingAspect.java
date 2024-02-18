package com.example.seminar8springAOP.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LooggingAspect {
    @Around("@annotation(com.example.seminar8springAOP.annotacions.LoggedExecution)")
    public Object LogExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();
        System.out.println(joinPoint.getSignature()+" start exec.");
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature()+" executed in "+executionTime+" ms");

        return proceed;
    }

}
