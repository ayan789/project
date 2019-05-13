package com.example.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsoApp.class)
@TestPropertySource("/application.properties")
public class BaseTest {

    @Configuration
    @ComponentScan(basePackages = {"com.example.demo"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class))
    static class ComponentScanConfig {

    }


    @Test
    public void contextLoads() {
    }
}

