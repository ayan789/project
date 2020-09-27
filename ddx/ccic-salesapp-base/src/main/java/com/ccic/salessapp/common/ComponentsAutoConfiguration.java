package com.ccic.salessapp.common;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigurationPackage
@EnableFeignClients(basePackages ={"com.ccic.salesapp"})
@ComponentScan(basePackages = {"com.ccic.salessapp.common"})
@AutoConfigureBefore(MybatisAutoConfiguration.class)
public class ComponentsAutoConfiguration {
}
