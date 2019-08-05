package com.example.tlyantimmer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulerTask {

    /**
     * executeTask:(每5秒中执行一次)
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void executeTask() {
        System.out.println("--executeTask方法，每隔5秒执行一次，当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}