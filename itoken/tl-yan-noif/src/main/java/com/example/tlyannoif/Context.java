package com.example.tlyannoif;

public class Context {
    //上下文就是通过渠道ID找到具体的实现类方法
    public String calRecharge(Integer channelId,Integer goodsId) throws Exception{
        StrategyFactory strategyFactory = StrategyFactory.getInstance();
        Strategy strategy = strategyFactory.create(channelId);
        return  strategy.calRecharge(channelId,goodsId);
    };
}
