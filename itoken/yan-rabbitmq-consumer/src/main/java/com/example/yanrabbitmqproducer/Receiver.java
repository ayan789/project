package com.example.yanrabbitmqproducer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author ChangLiang
 * @date 2018/5/29
 */
@Service
@RabbitListener(queues = "${rabbitmq.queue}")
public class Receiver {

    @RabbitHandler
    public void recievedMessage(Spittle spittle) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Recieved Message: " + spittle);
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println(totalTimeMillis);
    }
}
