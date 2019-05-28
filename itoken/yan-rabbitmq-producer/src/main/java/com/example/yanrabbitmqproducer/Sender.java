package com.example.yanrabbitmqproducer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author ChangLiang
 * @date 2018/5/29
 */
@Service
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange exchange;

    int dots = 0;
    int count = 0;

    public void send() {
        Spittle spittle = new Spittle(1,"");
        StringBuilder builder = new StringBuilder("Hello");
        if (dots++ == 3) {
            dots = 1;
        }
        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }

        builder.append(Integer.toString(++count));
        String message = builder.toString();
        spittle.setMessage(message);
        rabbitTemplate.convertAndSend(exchange.getName(),null, spittle);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
