package com.example.tlyannoif;

import javax.annotation.Resource;

/**
        * @author: LKP
        * @date: 2019/1/2
        */
@Pay(channelId = 1)
public class ICBCBankImpl implements Strategy {

//    @Resource
//    UserService userService;

    @Override
    public String calRecharge(Integer channelId,Integer goodsId) {
      UserService userService = (UserService) InjectionByHandUtil.getBean(UserService.class);
      User user = userService.getById(channelId);
      return user.getName();
    }
}

