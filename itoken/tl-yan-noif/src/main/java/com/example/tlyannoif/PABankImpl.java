package com.example.tlyannoif;

@Pay(channelId = 2)
public class PABankImpl implements Strategy {

    @Override
    public String calRecharge(Integer channelId,Integer goodsId) {
        return "平安银行";
    }
}