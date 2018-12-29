package com.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.cmq.Account;
import com.qcloud.cmq.Message;
import com.qcloud.cmq.Queue;
import com.qcloud.cmq.QueueMeta;
/**
 * 腾讯云CMQ消息服务---队列工具类
 * @Title: CmqQueueUtil.java
 * @Description:CMQ 分为队列和（主题-订阅）两种模式
 * 此工具类仅支持队列相关的功能  队列需要消费者去拉取消息 （模型为poll）
 * @version V1.0
 */
public class CmqQueueUtil {

    private static  String secretId="AKIDwlsfltxOdjoIHpKDYVnG5dCyLT6ol1rD";
    private static  String secretKey="uNDNCUl3dDp9Pg1QP4IpN9XfMYW6E4H1";
    //外网接口请求域名：cmq-queue-region.api.qcloud.com
    //内网接口请求域名：cmq-queue-region.api.tencentyun.com
    //region 需用具体地域替换：gz（广州），sh（上海），bj（北京）
    private static  String endpoint = "http://cmq-queue-sh.api.qcloud.com/";
    private static Account account = new Account(endpoint,secretId, secretKey);

    /**
     * 创建队列
     * @param queueName 队列名称
     * @param meta 队列相关参数对象
     * @return true 成功  false 失败
     */
    public static Boolean createQueue(String queueName,QueueMeta meta) {
        boolean flag=false;
        try {
            account.createQueue(queueName,meta);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过队列名称删除队列
     * @param queueName 队列名称
     * @return true 成功  false 失败
     */
    public static Boolean deleteQueue(String queueName )  {
        Boolean flag=false;
        try {
            account.deleteQueue(queueName);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过队列名称获取队列实例
     * @param queueName
     * @return
     */
    public static Queue getQueue(String queueName) {
        return  account.getQueue(queueName);
    }


    /**
     * 列出当前帐号下所有队列名字
     * @param likeName 模糊查询匹配项 “为查所有
     * @return  Map  总数totalCount
     */
    public static Map<String, Object> listQueue(String searchWord) {
        HashMap<String ,Object> dataMap=new HashMap<String ,Object>();
        ArrayList<String> listQueue = new ArrayList<String>();
        try {
            int totalCount = account.listQueue(searchWord==null?"":searchWord,-1,-1,listQueue);
            dataMap.put("totalCount", totalCount);
            dataMap.put("listQueue", listQueue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dataMap;
    }

    /**
     * 获取指定队列的相关属性
     * @param queueName
     * @return
     */
    public static QueueMeta getQueueAttributes(String queueName) {
        Queue queue = getQueue(queueName);
        QueueMeta queueAttributes = null;
        try {
            queueAttributes = queue.getQueueAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  queueAttributes;
    }

    /**
     * 发送单条消息到指定的队列
     * @param queueName 队列名称
     * @param msgBody  消息
     * @return   msgId 不为"" 即为成功
     */
    public static String sendMessage(String queueName,String msgBody) {
        Queue queue = getQueue(queueName);
        String msgId ="";
        try {
            msgId = queue.sendMessage(msgBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgId;

    }

    /**
     * 批量发送消息到指定的队列 目前批量消息数量不能超过 16 条
     * @param queueName
     * @param vtMsgBody 所有消息体大小之和不能超过64k
     * @return
     */
    public static List<String> batchSendMessage(String queueName,ArrayList<String> vtMsgBody) {
        Queue queue = getQueue(queueName);
        List<String> megIdList=null;
        try {
            megIdList=queue.batchSendMessage(vtMsgBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return megIdList;

    }

    /**
     *通过消息句柄删除消息
     * @param queueName 队列名称
     * @param receiptHandle 消息句柄,获取消息时由服务器返回
     * @return
     */
    public static boolean deleteMessage(String queueName,String receiptHandle) {
        Queue queue = getQueue(queueName);
        boolean flag=false;
        try {
            queue.deleteMessage(receiptHandle);//消息句柄,获取消息时由服务器返回
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  flag;
    }

    /**
     * 消费消息  从队列中拉去消息
     * @param queueName 队列的名称
     * @param pollingWaitSeconds 请求最长的轮询等待时间  相当于阻塞接收消息超时时间
     * @return Message 返回null表示没有消费消息  消息消费完成后续调用删除消息（deleteMessage）否则队列中此消息还能被再次消费
     */
    public static Message receiveMessage(String queueName,int pollingWaitSeconds) {
        Queue queue = getQueue(queueName);
        Message message =null;
        try {
            message= queue.receiveMessage(pollingWaitSeconds<0?10:pollingWaitSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;

    }

    /**
     * 批量消费指定队列的消息
     * @param queueName 队列名称
     * @param numOfMsg 本次消费的消息数量。取值范围 1-16
     * @param pollingWaitSeconds 请求最长的轮询等待时间  相当于阻塞接收消息超时时间
     * @return 返回null表示没有消费消息  消息消费完成后续调用删除消息（deleteMessage）否则队列中此消息还能被再次消费
     */
    public static List<Message> batchReceiveMessage(String queueName,int numOfMsg, int pollingWaitSeconds) {
        Queue queue = getQueue(queueName);
        List<Message> msgList=null;
        try {
            msgList= queue.batchReceiveMessage(numOfMsg,pollingWaitSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgList;

    }


    /**
     * 批量删除指定队列的消息
     * @param queueName 队列名称
     * @param vtReceiptHandle 消息句柄列表，消费消息时由服务器返回
     * @return
     */
    public static boolean batchDeleteMessage(String queueName,List<String> vtReceiptHandle) {
        boolean flag=false;
        Queue queue = getQueue(queueName);
        try {
            queue.batchDeleteMessage(vtReceiptHandle);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    public static void main(String[] args) {
        System.out.println("消息队列");
        String queueName="axrscmq01";
//		  	QueueMeta meta = new QueueMeta();
//			meta.pollingWaitSeconds = 10;
//			meta.visibilityTimeout = 10;
//			meta.maxMsgSize = 65536;
//			meta.msgRetentionSeconds = 345600;
//			createQueue(queueName,meta);//创建队列

        Queue queue = getQueue(queueName);
        try {
            System.out.println(queue.getQueueAttributes());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		  System.out.println(queue);//获取队列实例

//		  List<String> listQueue = listQueue("");
//		  System.out.println(listQueue);//查询账户下所有队列

//		  QueueMeta queueAttributes = getQueueAttributes(queueName);
//		  System.out.println(queueAttributes);//获取队列属性

//		  String sendMessage = sendMessage(queueName,"this is content");
//		  System.out.println(sendMessage);//向队列中发送消息

//		  Message receiveMessage = receiveMessage(queueName,10);
//		  System.out.println(receiveMessage);//从队列中消费消息 消费完成后需通过Message的消息句柄调用删除消息接口删除消息  否则此消息还能被继续消费

//		  boolean deleteMessage = deleteMessage(queueName,"receiptHandle");
//		  System.out.println(deleteMessage);//通过消息句柄（receiptHandle）从队列中删除消息

//		  Boolean deleteQueue = deleteQueue(queueName);
//		  System.out.println(deleteQueue);//删除队列

    }

}
