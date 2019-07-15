package com.example.tlyannoif;

/**
        * @author: LKP
        * @date: 2019/1/2
        */
@Pay(channelId = 1)
public class ICBCBankImpl implements Strategy {

    @Override
    public String calRecharge(Integer channelId,Integer goodsId) {
        return "我是ICBC";
    }
}

