package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public long proDone(String id){
        System.out.println("产品服务开始执行 id:" + id);
        try {
            //模拟耗时3000ms的商品服务
            Thread.sleep(10000);
            //模拟调用超时或者服务异常
            // throw new RuntimeException("Oh My God, OrderService Exception!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("产品服务执行结束");
        //假设产品服务返回值=5
        return 2;
    }

}

