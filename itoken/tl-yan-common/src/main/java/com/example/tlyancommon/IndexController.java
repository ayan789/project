package com.example.tlyancommon;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.FileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

@RestController
public class IndexController {

    // 该邮箱修改为你需要测试的邮箱地址
    private static final String TO_EMAIL = "shuai.yan@zatech.com";


    @GetMapping("/senEmail")
    public String senEmail() throws Exception {
        // 配置，一次即可
//        OhMyEmail.config(OhMyEmail.SMTP_163(false), "17621385669@163.com", "abcd1234");
        Properties props = new Properties();

        OhMyEmail.config(OhMyEmail.SMTP_QQ(false), "704627050@qq.com", "anavrqvbjmhrbddd");
        // 如果是企业邮箱则使用下面配置
//        OhMyEmail.config(OhMyEmail.SMTP_ENT_QQ(false), "xxx@qq.com", "*******");
        OhMyEmail.subject("这是一封测试网络资源作为附件的邮件")
                .from("小姐姐的邮箱hoo")
                .to(TO_EMAIL)
                .html("<h1 font=red>信件内容</h1>")
                .attachURL(new URL("https://avatars1.githubusercontent.com/u/2784452?s=40&v=4"), "测试图片.jpeg")
                .send();
        return "发送邮件成功";
    }

    @GetMapping("/readxls")
    public String simpleReadListStringV2007() throws Exception {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        System.out.println(data);
        return "OK";
    }

}
