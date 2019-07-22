package com.example.tlyancommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages={"com.alibaba","com.example.tlyancommon"})
@SpringBootApplication
public class TlYanCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlYanCommonApplication.class, args);
    }

}
