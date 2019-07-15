package com.example.tlyannoif;

public interface Strategy {
    //不同渠道获取不同银行
    String calRecharge(Integer channelId,Integer goodsId);
}
