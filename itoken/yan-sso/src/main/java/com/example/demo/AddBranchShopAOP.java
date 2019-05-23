package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

@Component
@Aspect
public class AddBranchShopAOP{
    private static final Logger logger = LoggerFactory.getLogger(AddBranchShopAOP.class);

    /**
     * 指定切方法
     */
    @Pointcut("execution(* com.example.demo.IndexController.index(..))")
    public void access() {
    }

//    @Before("access()")
//    public void deBefore(JoinPoint joinPoint) throws Throwable {
//        System.out.println("目标方法内的参数为"+ Arrays.asList(joinPoint.getArgs()));
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        request.setAttribute("startTime",System.currentTimeMillis());
//    }
//
//    @After("access()")
//    public void logServiceAccesster(JoinPoint joinPoint){
//        System.out.println("目标方法返回数据为"+ Arrays.asList(joinPoint.));
//        System.out.println("logServiceAccesster");
//    }

    // 通知（环绕）
    @Around("access()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        Object[] args = pjp.getArgs();
        String params = "";
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        try {

            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {
                if ("POST".equals(method)) {
                    Object object = args[0];
                    params = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
                } else if ("GET".equals(method)) {
                    params = queryString;
                }
                params = URLDecoder.decode(params,"utf-8");
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime);
            logger.info("requestMethod:{},url:{},params:{},responseBody:{},elapsed:{}ms.", method , uri,  params,
                    JSON.toJSONString(result,SerializerFeature.WriteMapNullValue),(endTime - startTime));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("log error : ",e);
        }
        System.out.println("返回："+result);
        return result;
    }

}