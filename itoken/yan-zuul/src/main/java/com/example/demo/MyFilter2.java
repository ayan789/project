package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter2 implements Filter{
    private Logger logger = LoggerFactory.getLogger(MyFilter2.class);

    //init按照FilterConfig顺序执行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("MyFilter2 ==> init method: init");
    }

    //doFilter按照Order顺序执行
    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //不需要过滤的url
        String[] urls = {"/login","/json",".js",".css",".ico",".jpg",".png"};
        String spath = req.getRequestURL().toString();
        System.out.println(spath);
        boolean flag = true;
        for (String str : urls) {
            if (spath.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }
        String jwtToken = request.getParameter("jwtToken");
        if (jwtToken != null) {
            logger.info(Token.ValidToken(jwtToken).toString());
            try {
                //解析TOKEN
                Map<String, Object> userMsg = Token.ValidToken(jwtToken);
                String uid =  userMsg.get("uid").toString();
                logger.info("Token sucess"+uid);
            }
            catch (Exception e){
                flag =false;
                logger.info("Token error");
                e.printStackTrace();
            }
        }
        System.out.println(flag);
        if(flag){
            logger.info("MyFilter2 ==> doFilter method: after");
            filterChain.doFilter(request, response);//执行请求
        }
        else{
            // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
            Map<String,String> errorResponse = new HashMap();
            errorResponse.put("code","-100");
            errorResponse.put("msg","无效Token");
            res.setContentType("application/json; charset=utf-8");
            res.setCharacterEncoding("UTF-8");
            String userJson = convertObjectToJson(errorResponse);
            logger.info(userJson);
            OutputStream out = res.getOutputStream();
            out.write(userJson.getBytes("UTF-8"));
            out.flush();
        }
    }

    @Override
    public void destroy() {
        logger.info("MyFilter2 ==> destroy method: destroy");
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
