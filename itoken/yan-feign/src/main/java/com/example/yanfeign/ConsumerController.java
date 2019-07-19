package com.example.yanfeign;

import com.google.common.util.concurrent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@RestController
public class ConsumerController {

    @Autowired
    private HomeFeignClient homeFeignClient;

    @Autowired
    private NoifFeignClient noifFeignClient;

    @GetMapping(value = "/hello")
    public String hello() {
        long start = System.currentTimeMillis();
        String str =  homeFeignClient.helloConsumer();
        long end = System.currentTimeMillis();
        String time = "总调用时间是：" + (end - start) + "毫秒";
        System.out.println(time);
        return "ok";

    }

    @Autowired
    ListeningExecutorService listeningExecutorService;

    @RequestMapping("/test/guava")
    public Map<String, Object> execute(String id) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        // 异步调用用户服务
        ListenableFuture<Long> userServiceFuture = listeningExecutorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return homeFeignClient.module1();
            }
        });

        // 异步调用订单服务
        ListenableFuture<Long> orderServiceFuture = listeningExecutorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return noifFeignClient.module2();
            }
        });


        //注册用户服务回调
        addCallBack(userServiceFuture);
        //注册订单服务回调
        addCallBack(orderServiceFuture);

        // .......
        // 对多个ListenableFuture的合并，返回一个当所有Future成功时返回多个Future返回值组成的List对象。
        // 注：当其中一个Future失败或者取消的时候，会怎样？
        final ListenableFuture<List<Long>> threeServicesFutures = Futures.successfulAsList(userServiceFuture, orderServiceFuture);

        //对于多个ListenableFuture的进行转换，返回一个新的ListenableFuture
        final ListenableFuture<Long> transform = Futures.transformAsync(threeServicesFutures, new AsyncFunction<List<Long>, Long>() {
            /**
             * 用给定的输入封装一个特定的ListenableFuture作为输出
             *
             * @param input 输入的ListenableFuture对象
             */
            @Override
            public ListenableFuture<Long> apply(List<Long> input) {

                if (input == null || input.isEmpty()) {
                    return null;
                }
                // 这里可以对input进行复杂的处理，返回最终的一个结果
                // 比如：对用户服务，订单服务，商品服务的远程调用结果进行封装
                long result = 0;
                for (Long serviceResult : input) {
                    if (serviceResult != null) {
                        result += serviceResult;
                    }
                }
                // 立即返回一个带返回值的ListenableFuture
//                System.out.println("result:"+result);
//                User user =  userService.getById((int)result);
//                System.out.println(user.getName());
                return Futures.immediateFuture(result);
            }
        }, listeningExecutorService);
        long end = System.currentTimeMillis();
        String time = "总调用时间是：" + (end - start) + "毫秒";
        //为什么要初始化4个容量的Map
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("time", time);
        resultMap.put("return", "OK");
        //结果
        return resultMap;
    }

    /**
     * 添加回调
     * @param listenableFuture
     */
    private void addCallBack(ListenableFuture<Long> listenableFuture) {
        Futures.addCallback(listenableFuture, new FutureCallback<Long>() {
            @Override
            public void onSuccess(Long result) {
                System.out.printf("服务调用成功,执行结果是%s%n",result);
            }
            @Override
            public void onFailure(Throwable t) {
                System.out.printf("服务调用异常%s%n", t);
            }
        }, listeningExecutorService);
    }
}
