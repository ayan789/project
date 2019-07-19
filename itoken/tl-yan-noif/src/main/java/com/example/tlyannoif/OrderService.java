package com.example.tlyannoif;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public long orderDone(String id){
        System.out.println("订单服务开始执行 id:" + id);
        try {
            //模拟耗时2000ms的订单服务
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("订单服务执行结束");
        //假设订单服务返回值=3
        return 3;
    }

}
