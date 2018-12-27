package com.example.demo;

import com.common.util.MailUtil;
import com.n22.ehero.base.xml.dom4j.XmlTool;
import org.dom4j.Document;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    //日志记录器
    Logger logger  =  LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {
        
        //System.out.println("System打印");
        //日志级别 trace<debug<info<warn<error
        //springBoot默认日志级别info
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");

    }

    @Test
    public void emailTest() throws Exception {
        Document doc = XmlTool.bulidXmlDocByFile("templatePath");
        String title = doc.selectSingleNode("/mail/title").getText();
        String content = doc.selectSingleNode("/mail/content").getText();
        content = content.replaceAll("#br#", "<br/>");
        content = content.replaceAll("#nbsp#", "&nbsp;");
        //测试使用 (测试用自己的)
        MailUtil.sendMail("smtp.126.com", "true", "zsxn_2016@126.com", "ftp123", "email", null, null, title, content);
    }
}

