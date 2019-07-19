package com.example.tlyannoif;

import com.google.common.util.concurrent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    ListeningExecutorService listeningExecutorService;

    @GetMapping("/home")
    public String index () {
        return "up tl-yan-noif";
    }

    @GetMapping("/module_2")
    public long module_2 () {
        System.out.println("noif service module_2");
        return 123;
    }

    @GetMapping("/noif/{id}")
    public String noif (@PathVariable("id") int id) throws Exception{
        Context context = new Context();
        String con = context.calRecharge(id,1);
        return con;
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") int id){
        User user = userService.getById(id);
        return user ;
    }

    /**
     * 测试代码
     */
    @RequestMapping("/test/single")
    public Map<String, Object> test () {
        //开始调用的时间
        long start = System.currentTimeMillis();
        //同步调用用户服务
        long userServiceResult = userService.userDone("111");
        //同步调用产品服务
        long itemServiceResult = productService.proDone("222");
        //同步调用订单服务
        long orderServiceResult = orderService.orderDone("333");
        //结束调用的时间
        long end = System.currentTimeMillis();
        String time = "总调用时间是：" + (end - start) + "毫秒";
        //计算结果
        long result = userServiceResult + orderServiceResult + itemServiceResult;
        //为什么要初始化4个容量的Map
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("time", time);
        resultMap.put("result", result);
        //结果
        return resultMap;
    }



    @RequestMapping("/test/guava")
    public String testGuava() {
        // 记录开始时间
        Long start = System.currentTimeMillis();
        // 一个耗时的任务
        ListenableFuture<Boolean> listenableFuture = listeningExecutorService.submit(() -> {
            Thread.sleep(5000);
            return true;
        });
        // 注册回调事件
        Futures.addCallback(listenableFuture, new FutureCallBackTask(), listeningExecutorService);
        // 记录结束时间
        Long end = System.currentTimeMillis();
        // 执行时间
        System.out.println("execute()方法执行结束了，耗时=" + (end - start) + "毫秒");
        System.out.println("-----------------------华丽的分割线-----------------------");
        return "执行成功";
    }

    @RequestMapping("/test/senior")
    public Map<String, Object> execute(String id) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        // 异步调用用户服务
        ListenableFuture<Long> userServiceFuture = listeningExecutorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return userService.userDone("1");
            }
        });

        // 异步调用订单服务
        ListenableFuture<Long> orderServiceFuture = listeningExecutorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return orderService.orderDone("222");
            }
        });

        // 异步调用产品服务
        ListenableFuture<Long> productServiceFuture = listeningExecutorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return productService.proDone("333");
            }
        });

        //注册用户服务回调
        addCallBack(userServiceFuture);
        //注册订单服务回调
        addCallBack(orderServiceFuture);
        //注册产品服务回调
        addCallBack(productServiceFuture);

        // .......
        // 对多个ListenableFuture的合并，返回一个当所有Future成功时返回多个Future返回值组成的List对象。
        // 注：当其中一个Future失败或者取消的时候，会怎样？
        final ListenableFuture<List<Long>> threeServicesFutures = Futures.successfulAsList(userServiceFuture, orderServiceFuture, productServiceFuture);

        //对于多个ListenableFuture的进行转换，返回一个新的ListenableFuture
        final ListenableFuture<Long> transform = Futures.transformAsync(threeServicesFutures, new AsyncFunction<List<Long>, Long>() {
            /**
             * 用给定的输入封装一个特定的ListenableFuture作为输出
             *
             * @param input 输入的ListenableFuture对象
             */
            @Override
            public ListenableFuture<Long> apply(@Nullable List<Long> input) {

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
            public void onSuccess(@Nullable Long result) {
                System.out.printf("服务调用成功,执行结果是%s%n",result);
            }
            @Override
            public void onFailure(Throwable t) {
                System.out.printf("服务调用异常%s%n", t);
            }
        }, listeningExecutorService);
    }
}
