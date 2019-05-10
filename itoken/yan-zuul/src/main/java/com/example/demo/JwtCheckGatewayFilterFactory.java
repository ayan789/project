//package com.example.demo;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.DataBuffer;
//import java.util.Map;
//
//public class JwtCheckGatewayFilterFactory {
//    private static final Logger log = LoggerFactory.getLogger(JwtCheckGatewayFilterFactory .class);
//    //定义用户认证登录接口
//    private static final String CURRENCY_URL="/currency/login";
//    //redis初始KEY值
//    private static final String LOGIN_USER = "login_user";
//
//    public void apply(HttpServletResponse response, HttpServletRequest request) {
//        String jwtToken = request.getParameter("jwtToken");
//        //从请求体Header中获取token
////        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJ1aWQiLCJzdGEiOjE1NTcyODYyNzgxOTAsImNvbXBhbnlJZCI6ImNvbXBhbnlJZCIsImRlcHRJZCI6ImRlcHRJZCIsInVzZXJUeXBlIjoidXNlclR5cGUiLCJleHAiOjE1NTg1ODIyNzgxOTB9.tLX3xMmc-d3m5EbkStOZB4egpVGJdETPUmn5QUY5KUE";
//        if (jwtToken != null) {
//            log.info(Token.ValidToken(jwtToken).toString());
//            //解析TOKEN
//            Map<String, Object> userMsg = Token.ValidToken(jwtToken);
//            Long uid = (Long) userMsg.get("uid");
//            //解析客户端传过来的TOKEN是否和缓存中的TOKEN相同，并且判断TOKEN过期时间是否大于当前时间
//            //不合法(响应未登录的异常)用户名或密码错误!
//            //登录已过期,请重新登录...
//            //设置headers
////            HttpHeaders httpHeaders = response.getHeaders();
////            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
////            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
//        }
//
//    }
//
//}
