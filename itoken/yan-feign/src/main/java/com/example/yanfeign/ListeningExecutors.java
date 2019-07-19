package com.example.yanfeign;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Component
public class ListeningExecutors {

    @Bean
    public ListeningExecutorService createListeningExecutorService() {
        // 创建线程池
        return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }
}