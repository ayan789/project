package com.ccic.salesapp.noncar.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@Order(1)
public class ControllerLogInterceptor {
    //创建Pointcut表示式，表示所有controller请求
    @Pointcut("execution(* com..*.controller..*(..))")
    private void controllerAspect() {
    }// 请求method前打印内容

    @Around(value = "controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //通过uuid关联请求参数和返回参数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        methodBefore(pjp, uuid);
        Object proceed =null;

            proceed = pjp.proceed();
            if (proceed != null){
                log.info("[{}]响应报文:{}", uuid, JSONObject.toJSON(proceed));
            }

        return proceed;
    }

    public void methodBefore(JoinPoint joinPoint, String uuid) {
        // 打印请求内容
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            request.setAttribute("requestId", uuid);

            Map<String, String> map = new HashMap<String, String>();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
            log.info("请求头参数 : " + JSONObject.toJSONString(map));

            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] objs = joinPoint.getArgs();
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
            Map<String, Object> paramMap = new HashMap<String, Object>();
            for (int i = 0; i < objs.length; i++) {
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    if (objs[i] instanceof BeanPropertyBindingResult){
                        continue;
                    }
                    if (objs[i] instanceof MultipartFile){
                        continue;
                    }
                    paramMap.put(argNames[i], objs[i]);
                }
            }
            if (paramMap.size() > 0) {
                log.info("[{}]url:{}请求报文:{}", uuid, request.getRequestURL().toString(), JSONObject.toJSONString(paramMap));
            }
        } catch (Exception e) {
            log.error("[{}]AOP methodBefore:", uuid, e);
        }
    }



}