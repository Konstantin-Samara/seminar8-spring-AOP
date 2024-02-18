package com.example.seminar8springAOP.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
public class ExceptionLoggingAspect {

@AfterThrowing(pointcut = "within(@org.springframework.stereotype.Service *)", throwing = "ex")
public void LogException(JoinPoint joinPoint, RuntimeException ex) {

    System.err.println("We are catch an EXCEPTION ("+ex.getMessage()+")in method : "+joinPoint.getSignature()+"\n in "
            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

}
}
