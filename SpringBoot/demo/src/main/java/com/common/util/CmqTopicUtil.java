package com.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.qcloud.cmq.Account;
import com.qcloud.cmq.Subscription;
import com.qcloud.cmq.SubscriptionMeta;
import com.qcloud.cmq.Topic;
import com.qcloud.cmq.TopicMeta;
/**
 * 腾讯云CMQ消息服务---（主题-订阅）-工具类
 * @Title: CmqTopicUtil.java
 * @Description: CMQ 分为队列和（主题-订阅）两种模式
 * 	此工具类仅支持主题模式相关的功能  主题模式可将发送到主题的消息主动推送（push）到指定的队列或URL
 * @date 2017年6月15日
 * @version V1.0
 */
public class CmqTopicUtil {

    private static  String secretId="";
    private static  String secretKey="";
    //外网接口请求域名：cmq-topic-region.api.qcloud.com
    //内网接口请求域名：cmq-topic-region.api.tencentyun.com
    //region 需用具体地域替换：gz（广州），sh（上海），bj（北京）
    private static  String endpoint = "http://cmq-topic-bj.api.qcloud.com/";
    private static Account account = new Account(endpoint,secretId, secretKey);

    /**
     * 创建主题
     * @param topicName 主题名称
     * @param filterType filterType =1或为空， 表示该主题下所有订阅使用 filterTag 标签过滤；
     * 					filterType =2 表示用户使用 bindingKey 过滤。
     * 					该参数设定之后不可更改。
     * @return true 成功  false 失败
     */
    public static Boolean createTopic(String topicName,int filterType) {
        boolean flag=false;
        try {
            account.createTopic(topicName, 65536,filterType==2? 2 : 1);//消息最大长度。取值范围 1024-65536 Byte（即1-64K），默认值 65536
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过主题名称获取主题实例
     * @param queueName
     * @return
     */
    public static Topic getTopic(String topicName) {
        return  account.getTopic(topicName);
    }

    /**
     * 获取主题属性
     * @param topicName 主题名称
     * @return TopicMeta！=null 成功
     */
    public static TopicMeta getTopicAttributes(String topicName) {
        Topic  topic= getTopic(topicName);
        TopicMeta topicMeta= null;
        try {
            topicMeta = topic.getTopicAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  topicMeta;
    }

    /**
     * 发送单条消息到指定的主题
     * @param topicName 主题名称
     * @param message  消息内容
     * @return   msgId 不为"" 即为成功
     */
    public static String publishMessage(String topicName,String message) {
        Topic topic = getTopic(topicName);
        String msgId ="";
        try {
            msgId = topic.publishMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgId;

    }

    /**
     * 批量发送消息到指定的主题  目前批量消息数量不能超过 16 条
     * @param topicName 主题名称
     * @param vMsg 消息正文 所有消息体大小之和不能超过64k
     * @return  megIdList！=null标识成功   服务器生成消息的唯一标识 Id列表，每个元素是一条消息的信息。
     */
    public static Vector<String> batchPublishMessage(String topicName,ArrayList<String> vMsg) {
        Topic topic = getTopic(topicName);
        Vector<String> megIdList=null;
        try {
            megIdList=topic.batchPublishMessage(vMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return megIdList;
    }
    /**
     * 批量发送消息到指定的主题  目前批量消息数量不能超过 16 条
     * @param topicName 主题名称
     * @param str 参数可指定多个  消息正文 所有消息体大小之和不能超过64k
     * @return  megIdList！=null标识成功   服务器生成消息的唯一标识 Id列表，每个元素是一条消息的信息。
     */
    public static Vector<String> batchPublishMessage(String topicName,String...str) {
        Topic topic = getTopic(topicName);
        Vector<String> megIdList=null;
        try {
            ArrayList<String> vMsg=new ArrayList<String>();
            for(String s:str){
                vMsg.add(s);
            }
            megIdList=topic.batchPublishMessage(vMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return megIdList;
    }

    /**
     * 列出当前帐号下所有主题（名字）
     * @param searchWord 模糊查询匹配项 “” 为查所有
     * @return  Map  总数totalCount  获取到的数据 listTopic 返回null代表错误
     */
    public static Map<String, Object> listTopic(String searchWord) {
        HashMap<String ,Object> dataMap=null;
        ArrayList<String> listTopic = new ArrayList<String>();
        try {
            int totalCount = account.listTopic(searchWord==null?"":searchWord,listTopic,-1,-1);
            dataMap=new HashMap<String ,Object>();
            dataMap.put("totalCount", totalCount);
            dataMap.put("listTopic", listTopic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dataMap;
    }


    /**
     * 创建订阅
     * @param topicName 主题名字，在单个地域同一帐号下唯一
     * @param subscriptionName 订阅名字，在单个地域同一帐号的同一主题下唯一
     * @param Protocal订阅的协议，目前支持两种协议：http、queue。
     * 					使用http协议，用户需自己搭建接受消息的web server。
     * 					使用queue，消息会自动推送到CMQ queue，用户可以并发地拉取消息
     * @param Endpoint  接收通知的endpoint，根据协议protocol区分：
     * 					对于http，endpoint必须以“http://”开头，host可以是域名或IP；
     * 					对于queue，则填queueName。
     * @return
     */
    public static boolean createSubscribe(String topicName,String subscriptionName,String Protocal,String Endpoint) {
        boolean flag=false;
        try {
            account.createSubscribe(topicName,subscriptionName,Endpoint, Protocal);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取订阅
     * @param topicName 主题名称
     * @param subscriptionName 订阅名称
     * @return Subscription！=null即为成功
     */
    public static Subscription getSubscription(String topicName,String subscriptionName) {
        Subscription  sub = null;
        try {
            sub = account.getSubscription(topicName,subscriptionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sub;

    }

    /**
     * 获取订阅属性
     * @param topicName 主题名称
     * @param subscriptionName 订阅名称
     * @return SubscriptionMeta！=null 即为成功
     */
    public static SubscriptionMeta getSubscriptionAttributes(String topicName,String subscriptionName) {
        Subscription  sub= getSubscription(topicName,subscriptionName);
        SubscriptionMeta subMeta= null;
        try {
            subMeta = sub.getSubscriptionAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  subMeta;
    }

    /**
     * 获取某个主题下所有订阅
     * @param topicName
     * @param searchWord 模糊查询匹配项 "" 为查所有
     * @return count 查到的数量 dataList订阅的名称
     */
    public static Map<String, Object> ListSubscription(String topicName,String searchWord) {
        Topic topic = getTopic(topicName);
        ArrayList<String> vSubscriptionList = new ArrayList<String>();
        HashMap<String ,Object> dataMap=new HashMap<String ,Object>();
        try {
            int SubscriptionCount = topic.ListSubscription(-1,-1,searchWord==null?"":searchWord,vSubscriptionList);
            dataMap.put("count", SubscriptionCount);
            dataMap.put("dataList", vSubscriptionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;

    }


    /**
     * 通过订阅名称删除主题中的订阅
     * @param topicName 主题名称
     * @param subscriptionName 订阅名称
     * @return true 成功  false 失败
     */
    public static Boolean deleteSubscribe(String topicName,String subscriptionName )  {
        Boolean flag=false;
        try {
            account.deleteSubscribe(topicName,subscriptionName);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过主题名称删除主题
     * @param topicName 主题名称
     * @return true 成功  false 失败
     */
    public static Boolean deleteTopic(String topicName )  {
        Boolean flag=false;
        try {
            account.deleteTopic(topicName);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
//			  String  topName="lyp001-top";
//			  Boolean createTopic = createTopic(topName,3);
//			  System.out.println(createTopic);//创建主题

//			  Topic topic = getTopic(topName);
//			  System.out.println(topic);//获取主题

//			  TopicMeta topicAttributes = getTopicAttributes(topName);
//			  System.out.println(topicAttributes);//获取主题属性

//			  String publishMessage = publishMessage(topName,"this my content");
//			  System.out.println(publishMessage);//向主题发消息  向主题发消息时该主题需要存在订阅者 否则会调用接口出错

//			  Vector<String> batchPublishMessage = batchPublishMessage(topName,"content","content2","content3");
//			  System.out.println(batchPublishMessage);//向主题发消息  向主题发消息时该主题需要存在订阅者 否则会调用接口出错

//			  boolean createSubscribe = createSubscribe(topName,"lyp001-top-sub","http","http://www.baidu.com");
//			  System.out.println(createSubscribe);//创建订阅

//			  Subscription subscription = getSubscription(topName,"lyp001-top-sub");
//			  System.out.println(subscription);//获取订阅

//			  SubscriptionMeta subscriptionAttributes = getSubscriptionAttributes(topName,"lyp001-top-sub");
//			  System.out.println(subscriptionAttributes);//获取主题属性

//			  Map<String, Object> listSubscription = ListSubscription(topName, "");
//			  System.out.println(listSubscription);//获取主题所有订阅

//			  Map<String, Object> listTopic = listTopic("");
//			  System.out.println(listTopic);

//			  Boolean deleteSubscribe = deleteSubscribe(topName,"lyp001-top-sub");
//			  System.out.println(deleteSubscribe);//删除主题中的订阅

//			  Boolean deleteTopic = deleteTopic(topName);
//			  System.out.println(deleteTopic);

    }
}
