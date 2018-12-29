package com.common.util;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;
/**
 *
 * @Title: XinGePushUtil.java
 * @Description: 信鸽推送工具类
 * @author lyp
 * @date 2017年5月24日
 * @version V1.0
 */
public class XinGePushUtil {
    /**信鸽Android  识别一个应用的唯一标识*/
    public static final Long ANDROID_ACCESS_ID = 2100258360L;
    /**信鸽Android   用于验证API调用*/
    public static final  String ANDROID_SECRET_KEY = "2ace62af8522d23ae009af816028305d";


    /**信鸽IOS 识别一个应用的唯一标识*/
    public static final Long IOS_ACCESS_ID = 2200258415L;
    /**信鸽IOS  用于验证API调用*/
    public static final String IOS_SECRET_KEY = "a2f270d22abb742a4d30ac898d73509e";


    private  static   final XingeApp AndroidPush=new XingeApp(ANDROID_ACCESS_ID, ANDROID_SECRET_KEY);
    private  static   final XingeApp IosPush=new XingeApp(IOS_ACCESS_ID, IOS_SECRET_KEY);



    public static void main(String[] args) {
//  	ios设备需要上传证书   目前全量推送 单个推送 标签推送用不了  查询类的能用  设置标签的能用

//    	System.out.println(pushAllAndroid("推送到所有安卓设备", "测试推送"));//发送到所有安卓设备 ok
//    	System.out.println(pushAllIos("推送到所有iOS色设备"));//发送到所有iOS设备 ok
//    	JSONObject ret = IosPush.queryInfoOfToken("6b01e28d171ae5259e3240a82dd22095040437b452b95409a3d708ab7525b286");
//    	System.out.println(ret);
//    	JSONObject pushTokenIos = pushTokenIos("测数信鸽推送by-token-ios", "6b01e28d171ae5259e3240a82dd22095040437b452b95409a3d708ab7525b286");
//    	System.out.println(pushTokenIos);

        // 设置标签
      /*List<TagTokenPair> pairs = new ArrayList<TagTokenPair>();
      	 pairs.add(new TagTokenPair("tag1", "6b01e28d171ae5259e3240a82dd22095040437b452b95409a3d708ab7525b286"));
         pairs.add(new TagTokenPair("tag2", "6b01e28d171ae5259e3240a82dd22095040437b452b95409a3d708ab7525b286"));
         JSONObject ret = IosPush.BatchSetTag(pairs);
         System.out.println(ret);*/

//    	 JSONObject pushTagIos = pushTagIos("测试标签推送","OR","tag1","tag2");
//    	 System.out.println(pushTagIos);

        JSONObject queryTags = IosPush.queryTokenTags("6b01e28d171ae5259e3240a82dd22095040437b452b95409a3d708ab7525b286");
        System.out.println(queryTags);



    }

    /**
     * Android 平台推送消息给所有设备
     * @param title
     * @param content
     * @return
     */
    public static JSONObject pushAllAndroid(String title ,String content) {
        return XingeApp.pushAllAndroid(ANDROID_ACCESS_ID, ANDROID_SECRET_KEY,title,content);
    }

    /**
     * Android 平台推送消息给所有设备  自定义的Message
     * @param message
     * @return
     */
    public static JSONObject pushAllAndroid(Message message) {
        return AndroidPush.pushAllDevice(0, message);
    }

    /**
     * Android 平台推送消息给单个设备  BY token
     * @param title
     * @param content
     * @param token
     * @return
     */
    public static JSONObject pushTokenAndroid(String title , String content, String token){
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        message.setType(Message.TYPE_MESSAGE);
        message.setExpireTime(86400);
        JSONObject ret = AndroidPush.pushSingleDevice(token, message);
        return ret;
    }

    /**
     * Android 平台推送消息给单个设备  BY token  AND  自定义的message
     * @param token
     * @param message
     * @return
     */
    public static JSONObject pushTokenAndroid(String token,Message message){
        JSONObject ret = AndroidPush.pushSingleDevice(token, message);
        return ret;
    }


    /**
     * Android 平台推送消息给标签--支持传入多个标签
     * @param title
     * @param content
     * @param tagsOp 连接符 默认为OR
     * @param tag 支持多个标签传入
     * @return
     */
    public static JSONObject pushTagAndroid(String title, String content,String tagsOp, String... tag) {
        Message message = new Message();
        message.setType(1);
        message.setTitle(title);
        message.setContent(content);
        List<String> tagList = new ArrayList<String>();
        for(String s:tag){
            tagList.add(s);
        }
        JSONObject ret = AndroidPush.pushTags(0, tagList,tagsOp==null?"OR":tagsOp ,message);
        return ret;
    }


    /**
     *  Android 平台推送消息给标签--支持传入多个标签---自定义message
     * @param title
     * @param content
     * @param tagsOp 默认为OR
     * @param tag 支持多个标签传入
     * @return
     */
    public static JSONObject pushTagAndroid(Message message,String tagsOp, String... tag) {
        List<String> tagList = new ArrayList<String>();
        for(String s:tag){
            tagList.add(s);
        }
        JSONObject ret = AndroidPush.pushTags(0, tagList,tagsOp==null?"OR":tagsOp ,message);
        return ret;
    }




    /**
     * IOS 平台推送消息给所有设备
     * @param content
     * @return
     */
    public static JSONObject pushAllIos(String content){
        return  XingeApp.pushAllIos(IOS_ACCESS_ID, IOS_SECRET_KEY, content,  XingeApp.IOSENV_PROD);
    }


    /**
     * IOS 平台推送消息给所有设备  自定义的Message
     * @param message
     * @return
     */
    public static JSONObject pushAllIos(MessageIOS message){
        return  IosPush.pushAllDevice(0, message,  XingeApp.IOSENV_PROD);
    }

    /**
     * IOS 平台推送消息给单个设备  BY token
     * @param content
     * @param token
     * @return
     */
    public static JSONObject pushTokenIos(String content, String token){
        MessageIOS message = new MessageIOS();
        message.setAlert(content);
        message.setBadge(1);
        message.setSound("beep.wav");
        return IosPush.pushSingleDevice(token, message,  XingeApp.IOSENV_DEV);
    }


    /**
     * IOS 平台推送消息给单个设备  BY token AND  自定义的message
     * @param content
     * @param token
     * @return
     */
    public static JSONObject pushTokenIos( String token,MessageIOS message){
        return IosPush.pushSingleDevice(token, message,  XingeApp.IOSENV_DEV);
    }


    /**
     * IOS 平台推送消息给标签--支持传入多个标签
     * @param content
     * @param tagsOp 默认为OR
     * @param tag  支持多个标签传入
     * @return
     */
    public static JSONObject pushTagIos( String content,String tagsOp, String... tag) {
        MessageIOS message = new MessageIOS();
        message.setAlert(content);
        message.setBadge(1);
        message.setSound("beep.wav");
        List<String> tagList = new ArrayList<String>();
        for(String s:tag){
            tagList.add(s);
        }
        return IosPush.pushTags(0, tagList, tagsOp==null?"OR":tagsOp, message, XingeApp.IOSENV_DEV);
    }

    /**
     * IOS 平台推送消息给标签--支持传入多个标签---自定义message
     * @param message
     * @param tagsOp
     * @param tag
     * @return
     */
    public static JSONObject pushTagIos( MessageIOS message,String tagsOp, String... tag) {
        List<String> tagList = new ArrayList<String>();
        for(String s:tag){
            tagList.add(s);
        }
        return IosPush.pushTags(0, tagList, tagsOp==null?"OR":tagsOp, message, XingeApp.IOSENV_DEV);
    }


}
