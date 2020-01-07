package com.example.tlyannoif;

import lombok.Data;

import java.util.function.Consumer;

@Data
public class ConsumerUtils {

    public static final ThreadLocal<Integer> insureBuild = new ThreadLocal<>();

    public static void main(String[] args) {
        ConsumerUtils consumerUtils = new ConsumerUtils();
        consumerUtils.testConsumer(3);
        consumerUtils.testConsumer2(ConsumerUtils.insureBuild.get());
        System.out.println(ConsumerUtils.insureBuild.get());
    }

    public void testConsumer(int a){
        Consumer<Integer> square = x -> insureBuild.set(x * x);
        square.accept(a);
    }

    public void testConsumer2(int a){
        Consumer<Integer> square = x -> insureBuild.set(x + x);
        square.accept(a);
    }

}


