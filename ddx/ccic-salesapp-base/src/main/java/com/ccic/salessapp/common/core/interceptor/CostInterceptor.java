package com.ccic.salessapp.common.core.interceptor;

import com.ccic.salessapp.common.core.logger.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CostInterceptor extends HandlerInterceptorAdapter {
    private final static Logger log=Logger.getLogger(CostInterceptor.class);
    private final static String  START_TIME="REQUEST-START-TIME";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(START_TIME,System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        log.info("{} cost {}ms",request.getRequestURL(),System.currentTimeMillis()-(long)request.getAttribute(START_TIME));
        Logger.LocalLogger.remove();
    }
}
