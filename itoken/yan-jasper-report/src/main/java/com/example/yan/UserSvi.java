package com.example.yan;

import java.util.ArrayList;
import java.util.List;

public class UserSvi {
    public static List<User> createUsers(){
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setSalt("hello jasper report");
        user.setPassword("多哈我会对萨哈嗲和滴哦啊基督教");

        User user1 = new User();
        user1.setSalt("我的借口来我家啊德克勒克");
        user1.setPassword("拉DSL卡桑德拉来了");
        list.add(user);
        list.add(user1);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(UserSvi.createUsers());
    }
}
