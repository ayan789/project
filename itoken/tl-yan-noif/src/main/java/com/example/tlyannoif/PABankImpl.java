package com.example.tlyannoif;


import javax.annotation.Resource;

@Pay(channelId = 2)
public class PABankImpl  implements Strategy {

//    @Resource
//    UserService userService;

    @Override
    public String calRecharge(Integer channelId,Integer goodsId) {
        UserService userService = (UserService) InjectionByHandUtil.getBean(UserService.class);
        User user = userService.getById(channelId);
        return user.getName();
    }

}