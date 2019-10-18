//package com.example.tlyanencrypt.aspect;
//
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//@Order(value = Ordered.HIGHEST_PRECEDENCE)
//public class ApiLimitAspect {
//
//    @Around("execution(* com.example.tlyanencrypt.controller.*.*(..))")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
