package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

public class MyFilter1 implements Filter{
    private Logger logger = LoggerFactory.getLogger(MyFilter1.class);
    //init按照FilterConfig顺序执行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("MyFilter1 ==> init method: init");
    }
    //doFilter按照Order顺序执行
    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(request, response);//执行请求
        logger.info("MyFilter1 ==> doFilter method: after");
    }

    @Override
    public void destroy() {
        logger.info("MyFilter1 ==> destroy method: destroy");
    }
}
