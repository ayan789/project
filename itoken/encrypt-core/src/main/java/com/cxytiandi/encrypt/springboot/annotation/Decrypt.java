package com.cxytiandi.encrypt.springboot.annotation;


import java.lang.annotation.*;

/**
 * @Description: 自定义注解 - 排除不需要进行过滤的URL
 * @author: weixiaohuai
 * @Date: 2019/10/16 16:54
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Decrypt {

	String value() default "";
	
}
