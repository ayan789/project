package com.example.tlyanepos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
//@ComponentScan({"lerrain.tool","com.document","com.neo","com.example.tlyanepos"})
public class TlYanEposApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlYanEposApplication.class, args);
    }

}
