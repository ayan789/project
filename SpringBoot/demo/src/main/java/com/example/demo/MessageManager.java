package com.example.demo;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageManager {
    @GetMapping("/push")
    public String aa(){
        return "极光推送接口";
    }

    String key = "d9c9d87b57cdbf3c49db138f";
    String secret = "d7a0468f42fc7eb56059ed2d";
    Map extras = null;
    String[] alias = {"accountId"};

    @GetMapping("/JPush")
    public Boolean JPush(String title, String alert,String accountId) throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("state", "zl.account.mySysMessage");
        title = "测试";
        alert = "测试";
        boolean flag = false;
        Boolean iosFlsg = this.Push_IOSorAndroid(secret, key, title, alert, null, alias, "IOS");
        if (iosFlsg) System.out.println("jpush IOS Push Ok");
        else System.out.println("jpush IOS Push Error");
        Boolean AndroidFlsg = this.Push_IOSorAndroid(secret, key, title, alert, map, alias, "Android");
        if (AndroidFlsg) System.out.println("jpush ANDROID Push Ok");
        else System.out.println("jpush ANDROID Push Error");
        if(iosFlsg || AndroidFlsg) {
            flag = true;
        }
        System.out.println("jpush all end");
        return flag;
    }

    /**
     * 推送消息
     */
    public Boolean jpush2(String title, String alert, Map extras, String[] alias) throws Exception {
        Boolean flag = false;
        //LogTool.inf(this.getClass(), "jpush start...");
        //LogTool.inf(this.getClass(), "jpush param : title = " + title + ",alert = " + alert + ",extras = " + extras.toString() + ",alias = " + alias[0]);
        Boolean iosFlsg = this.Push_IOSorAndroid(secret, key, title, alert, extras, alias, "IOS");
        if (iosFlsg) System.out.println("jpush IOS Push Ok");
        else System.out.println("jpush IOS Push Error");
        Boolean AndroidFlsg = this.Push_IOSorAndroid(secret, key, title, alert, extras, alias, "Android");
        if (AndroidFlsg) System.out.println("jpush ANDROID Push Ok");
        else System.out.println("jpush ANDROID Push Ok");
        if(iosFlsg | AndroidFlsg) {
            flag = true;
        }
        //LogTool.inf(this.getClass(), "jpush all end");
        return flag;
    }

    public void JPushAll(String content) throws Exception {
        Map<String,String> map = new HashMap<String, String>();
        map.put("state", "zl.account.mySysMessage");
        //LogTool.inf(this.getClass(), "jpush all start...");
        //LogTool.inf(this.getClass(), "jpush param : title = " + content);
        JPushClient jPushClient = new JPushClient(secret, key, null, ClientConfig.getInstance());
        PushPayload payload =  PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(content)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
        PushResult result;
        try {
            result = jPushClient.sendPush(payload);
            Thread.sleep(5000);
            // 请求结束后，调用 NettyHttpClient 中的 close 方法，否则进程不会退出。
            jPushClient.close();
            //LogTool.inf(this.getClass(), "jpushAllResult = " + result.isResultOK() + ":" + result.getResponseCode() + "," + result.getOriginalContent());
            //LogTool.inf(this.getClass(), "jpush all end");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            jPushClient.close();
        }

    }

    /**
     * 极光推送
     * @param title 推送题目
     * @param alert 推送内容
     * @param extras 推送路由
     * @param alias 推送目标
     * @return
     */
    @GetMapping("/messagePush")
    public  boolean Push_IOSorAndroid (String secret, String key, String title, String alert, Map extras, String[] alias, String platform) {
        System.out.println("进入messagePush");

        try {
            JPushClient jPushClient = new JPushClient(secret, key, null, ClientConfig.getInstance());
            PushPayload payload = null;
            if(platform.equals("IOS")) {
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.ios())
                        .setAudience(Audience.alias(alias))
                        .setNotification(Notification.newBuilder()
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .setAlert(alert)
                                        .addExtras(extras)
                                        .build())
                                .build())
                        .setMessage(Message.newBuilder()
                                .setMsgContent(alert)
                                .build())
                        .setOptions(Options.newBuilder()
                                .setApnsProduction(true)
                                .build())
                        .build();
            } else if (platform.equals("Android")) {
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.android())
                        .setAudience(Audience.alias(alias))
                        .setNotification(Notification.newBuilder()
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle(title)
                                        .setAlert(alert)
                                        .addExtras(extras)
                                        .build())
                                .build())
                        .setMessage(Message.newBuilder()
                                .setMsgContent(alert)
                                .build())
                        .setOptions(Options.newBuilder()
                                .setApnsProduction(true)
                                .build())
                        .build();
            }
            PushResult result = jPushClient.sendPush(payload);
            Thread.sleep(5000);
            // 请求结束后，调用 NettyHttpClient 中的 close 方法，否则进程不会退出。
            jPushClient.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //推送消息（根据不同的用户角色推送）
    //查找未推送的信息
    //删除消息
    //获取消息列表,适用于数据量较小的情况
    //加载消息
    //保存消息
    //修改消息阅读状态
    //分装消息entity
}
