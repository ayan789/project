package com.example.tlyanepos;


import com.application.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;


@Component
public class MyFutureTask {


    @Resource
    SInsureService sInsureService;

    /**
     * 核心线程 8 最大线程 20 保活时间30s 存储队列 10 有守护线程 拒绝策略:将超负荷任务回退到调用者
     */
    private static ExecutorService executor = new ThreadPoolExecutor(8, 20,
            30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
            new ThreadFactoryBuilder().setNameFormat("User_Async_FutureTask-%d").setDaemon(true).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    @SuppressWarnings("all")
    public SInsure getUserAggregatedResult(final Long userId) {
        System.out.println("MyFutureTask的线程:" + Thread.currentThread());
        SPay spay = new SPay();
        SInformData sInformData = new SInformData();
        SCustomer sCustomer = new SCustomer();
        SBeneficiary sBeneficiary = new SBeneficiary();
        try {
            Future<SPay> getSpay = executor.submit(() -> sInsureService.getSpay(100L));
            Future<SInformData> getSInformData = executor.submit(() -> sInsureService.getSInformData(100L));
            Future<SCustomer> getSCustomer = executor.submit(() -> sInsureService.getScustomer(100L));
            Future<SBeneficiary> getSBeneficiary = executor.submit(() -> sInsureService.getSBeneficiary(100L));
            //get阻塞
            spay = getSpay.get();
            sInformData = getSInformData.get();
            sCustomer = getSCustomer.get();
            sBeneficiary = getSBeneficiary.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println((">>>>>>聚合查询用户聚合信息异常:" + e + "<<<<<<<<<"));
        }
        SInsure sInsure =
                SInsure.builder().pay(spay).informData(sInformData)
                        .applicant(sCustomer).beneficiary(sBeneficiary).build();
        return sInsure;
    }
}
