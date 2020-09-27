package com.ccic.salesapp.noncar.controller;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);
   

    public static void main(String[] args) {
        //long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                	Demo1 demo1 = new Demo1();
                	demo1.buildAa();
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(System.currentTimeMillis() - startTime);
    }
    
    public void buildAa() {
    	System.out.println("buildAa");
    }
}
