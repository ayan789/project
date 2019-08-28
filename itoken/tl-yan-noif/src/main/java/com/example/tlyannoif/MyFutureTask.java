package com.example.tlyannoif;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;


@Component
public class MyFutureTask {


    @Resource
    InterfaceService interfaceService2;

    /**
     * 核心线程 8 最大线程 20 保活时间30s 存储队列 10 有守护线程 拒绝策略:将超负荷任务回退到调用者
     */
    private static ExecutorService executor = new ThreadPoolExecutor(8, 20,
            30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
            new ThreadFactoryBuilder().setNameFormat("User_Async_FutureTask-%d").setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    @SuppressWarnings("all")
    public UserBehaviorDataDTO getUserAggregatedResult(final Long userId) {

        System.out.println("MyFutureTask的线程:" + Thread.currentThread());


        long fansCount = 0, msgCount = 0, collectCount = 0,
                followCount = 0, redBagCount = 0, couponCount = 0;

//        fansCount = userService.countFansCountByUserId(userId);
//        msgCount = userService.countMsgCountByUserId(userId);
//        collectCount = userService.countCollectCountByUserId(userId);
//        followCount = userService.countFollowCountByUserId(userId);
//        redBagCount = userService.countRedBagCountByUserId(userId);
//        couponCount = userService.countCouponCountByUserId(userId);

        try {

            Future<Long> fansCountFT = executor.submit(() -> interfaceService2.countFansCountByUserId(userId));
            Future<Long> msgCountFT = executor.submit(() -> interfaceService2.countMsgCountByUserId(userId));
            Future<Long> collectCountFT = executor.submit(() -> interfaceService2.countCollectCountByUserId(userId));
            Future<Long> followCountFT = executor.submit(() -> interfaceService2.countFollowCountByUserId(userId));
            Future<Long> redBagCountFT = executor.submit(() -> interfaceService2.countRedBagCountByUserId(userId));
            Future<Long> couponCountFT = executor.submit(() -> interfaceService2.countCouponCountByUserId(userId));

            //get阻塞
            fansCount = fansCountFT.get();
            msgCount = msgCountFT.get();
            collectCount = collectCountFT.get();
            followCount = followCountFT.get();
            redBagCount = redBagCountFT.get();
            couponCount = couponCountFT.get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println((">>>>>>聚合查询用户聚合信息异常:" + e + "<<<<<<<<<"));
        }
        UserBehaviorDataDTO userBehaviorData =
                UserBehaviorDataDTO.builder().fansCount(fansCount).msgCount(msgCount)
                        .collectCount(collectCount).followCount(followCount)
                        .redBagCount(redBagCount).couponCount(couponCount).build();
        return userBehaviorData;
    }


}
