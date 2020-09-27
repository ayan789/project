
package com.ccic.salessapp.common.core.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class BeansContext {
    @Autowired
    ApplicationContext applicationContext;
    @PostConstruct
    private final void init() {
        context=applicationContext;
    }
    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {
        return (context != null)?context.getBean(clazz):null;
    }
}
