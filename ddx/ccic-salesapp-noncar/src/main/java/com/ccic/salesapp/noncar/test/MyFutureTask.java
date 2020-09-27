package com.ccic.salesapp.noncar.test;


import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementDetaiReq;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanElementDetailDto;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.ArrayList;
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
    public HttpVo getBuildAll(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
        System.out.println("MyFutureTask的线程:" + Thread.currentThread());
        HttpResult<?> res = null;
        HttpResult<?> res1 = null;
        HttpResult<?> res2 = null;
        HttpResult<?> res3 = null;
        HttpResult<?> res4 = null;
        try {
        	Future<HttpResult<?>> gres = executor.submit(() -> sInsureService.buildBackReasoningFactor(planElementDetailDto, planElementDetaiReq));
            Future<HttpResult<?>> gres1 = executor.submit(() -> sInsureService.buildHeader(planElementDetailDto, planElementDetaiReq));
            Future<HttpResult<?>> gres2 = executor.submit(() -> sInsureService.buildBottom(planElementDetailDto, planElementDetaiReq));
            Future<HttpResult<?>> gres3 = executor.submit(() -> sInsureService.buildCommonProblem(planElementDetailDto, planElementDetaiReq));
            Future<HttpResult<?>> gres4 = executor.submit(() -> sInsureService.buildFormulaList(planElementDetailDto, planElementDetaiReq));
            //get阻塞
            res = gres.get();
            res1 = gres1.get();
            res2 = gres2.get();
            res3 = gres3.get();
            res4 = gres4.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println((">>>>>>聚合查询用户聚合信息异常:" + e + "<<<<<<<<<"));
        }
		HttpVo httpVo = HttpVo.builder().res(res).res1(res1).res2(res2).res3(res3).res4(res4).build();
        return httpVo;
    }
    
    
    

}
