package com.example.tlyannoif;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QazAspect {

    @Pointcut(value = "@annotation( com.example.tlyannoif.EnableQazSecurity)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //访问目标方法的参数：
        Object[] args = joinPoint.getArgs();
        System.out.println("args:"+args[0]);

        String methodName = signature.getMethod().getName();
        System.out.println("方法名：" + methodName);

        if(!validate(args[0].toString())){
            return "没有权限";
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }
    }

    private boolean validate(String a){
        // TODO 实现自己的鉴权功能
        if(a.equals("123456")){
            return true;
        }
        return false;
    }




}
