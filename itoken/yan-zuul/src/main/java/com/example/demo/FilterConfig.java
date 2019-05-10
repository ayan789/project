package com.example.demo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public Filter MyFilter2(){
        return new MyFilter2();//自定义的过滤器
    }
    @Bean
    public Filter MyFilter1(){
        return new MyFilter1();//自定义的过滤器
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(6);//order的数值越小 则优先级越高
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean1(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(7);
        return bean;
    }

//    @Bean(name="filterConfig")
//    public FilterConfig filterConfig() {
//        return new FilterConfig();
//    }
}
