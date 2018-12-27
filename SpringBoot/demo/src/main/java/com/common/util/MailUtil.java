package com.common.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.n22.ehero.base.tool.LogTool;
import com.n22.ehero.base.tool.PropertiesTool;
import com.n22.ehero.base.xml.dom4j.XmlTool;
import org.apache.commons.mail.HtmlEmail;
import org.dom4j.Document;

public class MailUtil {

    public static void main(String[] args){
        try {
            String host = "";
            String auth = "";
            String userName = "";
            String from = "";
            String password = "";
            String receivers = "";//以英文逗号分隔
            String ccs = "";//以英文逗号分隔
            String bccs = "";//以英文逗号分隔
            String title = "";
            String content = "";
            String attachments = "";//以英文逗号分隔
            String attachmentNames = "";//以英文逗号分隔
            //MailUtil.sendMail(host, auth, userName, password, receivers, ccs, bccs, title, content);
            //MailUtil.sendMail(host, auth, userName, password, from, receivers, ccs, bccs, title, content, attachments, attachmentNames);
            MailUtil.sendHtmlMail(host, userName, password, from, receivers, ccs, bccs, title, content, attachments, attachmentNames);
            //MailUtil.sendMailByNoPwd(host, userName, receivers, ccs, bccs, title, content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     */
    public static void sendMail(String host, String auth, String userName, String password, String receivers, String ccs, String bccs, String title, String content) throws Exception {

        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host / mail.user / mail.from
         */
        // 配置发送邮件的环境属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", auth);
        // 发件人的账号
        props.put("mail.user", userName);
        // 访问SMTP服务时需要提供的密码(是授权码，而非用户登录密码)
        props.put("mail.password", password);

        // 构建授权信息，用于进行SMTP进行身份验证
        MyAuthenticator myAuthenticator = new MyAuthenticator(userName,password);
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, myAuthenticator);
        // 开启debug监听
        mailSession.setDebug(true);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        message.setFrom(new InternetAddress(userName));
        // 设置收件人
        String[] receiverArr = receivers.split(",");
        InternetAddress[] receiverAddress = new InternetAddress[receiverArr.length];
        for(int i = 0; i < receiverArr.length; i++){
            receiverAddress[i] = new InternetAddress(receiverArr[i]);
        }
        message.setRecipients(RecipientType.TO,receiverAddress);
        // 设置抄送
        if(ccs != null && !"".equals(ccs)){
            String[] ccArr = ccs.split(",");
            InternetAddress[] ccAddress = new InternetAddress[ccArr.length];
            for(int i = 0; i < ccArr.length; i++){
                ccAddress[i] = new InternetAddress(ccArr[i]);
            }
            message.setRecipients(RecipientType.CC,ccAddress);
        }
        // 设置密送，其他的收件人不能看到密送的邮件地址
        if(bccs != null && !"".equals(bccs)){
            String[] bccArr = bccs.split(",");
            InternetAddress[] bccAddress = new InternetAddress[bccArr.length];
            for(int i = 0; i < bccArr.length; i++){
                bccAddress[i] = new InternetAddress(bccArr[i]);
            }
            message.setRecipients(RecipientType.BCC,bccAddress);
        }
        // 设置邮件标题
        message.setSubject(title);
        // 设置邮件的内容体(HTML格式)
        message.setContent(content,"text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
    }

    /**
     * 发送邮件
     */
    public static void sendMail(String host, String auth, String userName, String password, String from, String receivers, String ccs, String bccs, String title, String content, String attachments, String attachmentNames) throws Exception {
        System.out.println(from);
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host / mail.user / mail.from
         */
        // 配置发送邮件的环境属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.stmp.host", host);
        props.put("mail.stmp.auth", auth);
        // 发件人的账号
        props.put("mail.user", userName);
        // 访问SMTP服务时需要提供的密码(是授权码，而非用户登录密码)
        props.put("mail.password", password);

        // 构建授权信息，用于进行SMTP进行身份验证
        MyAuthenticator myAuthenticator = new MyAuthenticator(userName,password);
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, myAuthenticator);
        // 开启debug监听
        mailSession.setDebug(true);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        message.setFrom(new InternetAddress(from));
        // 设置收件人
        String[] receiverArr = receivers.split(",");
        InternetAddress[] receiverAddress = new InternetAddress[receiverArr.length];
        for(int i = 0; i < receiverArr.length; i++){
            receiverAddress[i] = new InternetAddress(receiverArr[i]);
        }
        message.setRecipients(RecipientType.TO,receiverAddress);
        // 设置抄送
        if(ccs != null && !"".equals(ccs)){
            String[] ccArr = ccs.split(",");
            InternetAddress[] ccAddress = new InternetAddress[ccArr.length];
            for(int i = 0; i < ccArr.length; i++){
                ccAddress[i] = new InternetAddress(ccArr[i]);
            }
            message.setRecipients(RecipientType.CC,ccAddress);
        }
        // 设置密送，其他的收件人不能看到密送的邮件地址
        if(bccs != null && !"".equals(bccs)){
            String[] bccArr = bccs.split(",");
            InternetAddress[] bccAddress = new InternetAddress[bccArr.length];
            for(int i = 0; i < bccArr.length; i++){
                bccAddress[i] = new InternetAddress(bccArr[i]);
            }
            message.setRecipients(RecipientType.BCC,bccAddress);
        }
        // 设置邮件标题
        message.setSubject(title);
        // 设置邮件内容
        MimeMultipart mimeMultipart = new MimeMultipart();
        // 设置邮件的内容体(HTML格式)
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(content,"text/html;charset=UTF-8");
        mimeMultipart.addBodyPart(textPart);
        // 设置邮件的附件体
        if(attachments != null && !"".equals(attachments)){
            String[] attachmentArr = attachments.split(",");
            String[] attachmentNameArr = attachmentNames == null || "".equals(attachmentNames) ? null : attachmentNames.split(",");
            for(int i = 0; i < attachmentArr.length; i++){
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataHandler dataHandler = new DataHandler(new FileDataSource(attachmentArr[i]));  // 读取本地文件
                attachmentPart.setDataHandler(dataHandler);
                if(attachmentNameArr != null && attachmentNameArr[i] != null && !"".equals(attachmentNameArr[i])){
                    attachmentPart.setFileName(MimeUtility.encodeText(attachmentNameArr[i]));
                }
                mimeMultipart.addBodyPart(attachmentPart);
            }
        }
        // 设置邮件内容关系
        mimeMultipart.setSubType("mixed");//混合关系
        // 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mimeMultipart);
        // 发送邮件
        Transport.send(message);
    }

    /**
     * 发送邮件(不使用授权码)
     * @param host
     * @param userName
     * @param receiver
     * @param cc
     * @param bcc
     * @param title
     * @param content
     * @throws Exception
     */
    public static void sendMailByNoPwd(String host, String userName, String receivers, String ccs, String bccs, String title, String content) throws Exception {

        // 配置发送邮件的环境属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.host", host);
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props);
        //开启debug监听
        mailSession.setDebug(true);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        message.setFrom(new InternetAddress(userName));
        // 设置收件人
        String[] receiverArr = receivers.split(",");
        InternetAddress[] receiverAddress = new InternetAddress[receiverArr.length];
        for(int i = 0; i < receiverArr.length; i++){
            receiverAddress[i] = new InternetAddress(receiverArr[i]);
        }
        message.setRecipients(RecipientType.TO,receiverAddress);
        // 设置抄送
        if(ccs != null && !"".equals(ccs)){
            String[] ccArr = ccs.split(",");
            InternetAddress[] ccAddress = new InternetAddress[ccArr.length];
            for(int i = 0; i < ccArr.length; i++){
                ccAddress[i] = new InternetAddress(ccArr[i]);
            }
            message.setRecipients(RecipientType.CC,ccAddress);
        }
        // 设置密送，其他的收件人不能看到密送的邮件地址
        if(bccs != null && !"".equals(bccs)){
            String[] bccArr = bccs.split(",");
            InternetAddress[] bccAddress = new InternetAddress[bccArr.length];
            for(int i = 0; i < bccArr.length; i++){
                bccAddress[i] = new InternetAddress(bccArr[i]);
            }
            message.setRecipients(RecipientType.BCC,bccAddress);
        }
        // 设置邮件标题
        message.setSubject(title);
        // 设置邮件的内容体(HTML格式)
        message.setContent(content,"text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
    }

    /**
     * 发送邮件
     * @param host 邮件服务器地址
     * @param userName 用户名
     * @param password 密码
     * @param from 系统邮箱（发送邮邮箱）
     * @param receivers 收件邮箱
     * @param ccs 抄送
     * @param bccs 密送
     * @param title 邮件标题
     * @param content 邮件内容
     * @param attachments 附件地址
     * @param attachmentNames 附件名称
     * @return
     * @throws Exception
     */
    public static String sendHtmlMail(String host, String userName, String password, String from, String receivers, String ccs, String bccs, String title, String content, String attachments, String attachmentNames) throws Exception {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setAuthentication(userName, password);

        // 设置发件人
        email.setFrom(from);
        // 设置收件人
        String[] receiverArr = receivers.split(",");
        for(int i = 0; i < receiverArr.length; i++){
            email.addTo(receiverArr[i]);
        }
        // 设置抄送
        if(ccs != null && !"".equals(ccs)){
            String[] ccArr = ccs.split(",");
            for(int i = 0; i < ccArr.length; i++){
                email.addCc(ccArr[i]);
            }
        }
        // 设置密送，其他的收件人不能看到密送的邮件地址
        if(bccs != null && !"".equals(bccs)){
            String[] bccArr = bccs.split(",");
            for(int i = 0; i < bccArr.length; i++){
                email.addBcc(bccArr[i]);
            }
        }

        // 设置邮件编码
        email.setCharset("UTF-8");
        // 设置邮件标题
        email.setSubject(title);
        // 设置邮件内容
        MimeMultipart mimeMultipart = new MimeMultipart();
        // 设置邮件的内容体(HTML格式)
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(content,"text/html;charset=UTF-8");
        mimeMultipart.addBodyPart(textPart);
        // 设置邮件的附件体
        if(attachments != null && !"".equals(attachments)){
            String[] attachmentArr = attachments.split(",");
            String[] attachmentNameArr = attachmentNames == null || "".equals(attachmentNames) ? null : attachmentNames.split(",");
            for(int i = 0; i < attachmentArr.length; i++){
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataHandler dataHandler = new DataHandler(new FileDataSource(attachmentArr[i]));  // 读取本地文件
                attachmentPart.setDataHandler(dataHandler);
                if(attachmentNameArr != null && attachmentNameArr[i] != null && !"".equals(attachmentNameArr[i])){
                    attachmentPart.setFileName(MimeUtility.encodeText(attachmentNameArr[i]));
                }
                mimeMultipart.addBodyPart(attachmentPart);
            }
        }
        // 设置邮件内容
        email.addPart(mimeMultipart);

        String status = email.send();
        return status;
    }

    /**
     * 发送邮件
     */
    public void sendMail(String proposalId,String path,String receivers,String agentName,String customerName, String attachmentNames) throws Exception {

//        String templatePath = SpringTool.getServletContext().getRealPath("/WEB-INF/classes/") + "/template/mail/mail-proposal.xml";


        Document doc  = XmlTool.bulidXmlDocByFile("/template/mail/mail-proposal.xml");
        String title = doc.selectSingleNode("/mail/title").getText();
        String content = doc.selectSingleNode("/mail/content").getText();

        content = content.replaceAll("#br#", "<br/>");
        content = content.replaceAll("#nbsp#", "&nbsp;");
        content = content.replace("#customerName#", customerName);
        content = content.replace("#agentName#", agentName);

        String host = new PropertiesTool("config.properties").getProperty("common.mail.host");
        String userName = new PropertiesTool("config.properties").getProperty("common.mail.username");
        String password = new PropertiesTool("config.properties").getProperty("common.mail.password");
        String from = new PropertiesTool("config.properties").getProperty("common.mail.from");

        String status = MailUtil.sendHtmlMail(host, userName, password, from, receivers, null, null, title, content, path, attachmentNames);
        LogTool.inf(this.getClass(), "sendHtmlMail-status:" + status);
    }


}
