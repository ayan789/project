package com.example.tlyanencrypt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
public class IndexController extends BaseController {

    public static ThreadLocal<Integer> insureBuild = new ThreadLocal<>();

    @GetMapping("/hello2")
    public Object hello2() {
        IndexController consumerUtils = new IndexController();
        consumerUtils.testConsumer(3);
        consumerUtils.testConsumer2(IndexController.insureBuild.get());
        System.out.println(IndexController.insureBuild.get());
        return "ok";
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
