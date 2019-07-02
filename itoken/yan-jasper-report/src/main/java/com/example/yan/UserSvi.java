package com.example.yan;

import java.util.ArrayList;
import java.util.List;

public class UserSvi {
    public static List<User> createUsers(){
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setSalt("hello jasper report");
        user.setPassword("aaa");
        list.add(user);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(UserSvi.createUsers());
    }
}
