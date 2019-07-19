package com.example.tlyannoif;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getById(Integer id){
        return userDao.getById(id);
    }

    public long userDone(String id){
        System.out.println("用户服务开始执行 id:" + id);
//        User user = userDao.getById(Integer.parseInt(id));
//        System.out.println("yyyyyy:" + user.getName());
        try {
            //模拟耗时1000ms的用户服务
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("用户服务执行结束");
        //假设用户服务返回值=1
        return 1;
    }
}

