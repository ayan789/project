package com.example.tlyanencrypt.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.tlyanencrypt.dto.BookDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QazAspect {

    @Pointcut(value = "@annotation( com.example.tlyanencrypt.controller.EnableQazSecurity)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //访问目标方法的参数：
        Object[] args = joinPoint.getArgs();
//        System.out.println("args:"+args[0]);
//        System.out.println("args1:"+args[1]);
//
//        System.out.println("args2:"+args[2]);

        System.out.println(JSONObject.toJSONString(args[0]));
        BookDto aa = JSONObject.parseObject(JSONObject.toJSONString(args[0]), BookDto.class);
        System.out.println("log打印"+ aa.getPrice());

        String methodName = signature.getMethod().getName();
        System.out.println("方法名：" + methodName);


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
