package com.example.tlyancommon;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Table;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


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

    @GetMapping("/readxls2string2007")
    public String simpleReadListStringV2007() throws Exception {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        System.out.println(data);
        return "OK";
    }

    @GetMapping("/readxls2string2003")
    public String simpleReadListStringV2003() throws Exception {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2003.xls");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        System.out.println(data);
        return "OK";
    }

    @GetMapping("/readxls2model")
    public String simpleReadJavaModelV2007() throws Exception {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(2, 1, ReadModel.class));
        inputStream.close();
        System.out.println(data);
        return "OK";
    }

    @GetMapping("/readxls1000")
    public String readxls1000() throws Exception {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(2, 1, ReadModel.class), excelListener);
        inputStream.close();
        return "OK";
    }

    @GetMapping("/writexls")
    public String writeV2007() throws Exception {
        OutputStream out = new FileOutputStream("/Users/yanshuai/project/project/itoken/easyexcel-master/src/test/java/com/alibaba/easyexcel/test/listen/2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(DataUtil.createTestListObject(), sheet1);

        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null);
        sheet2.setTableStyle(DataUtil.createTableStyle());
        //writer.write1(null, sheet2);
        writer.write(DataUtil.createTestListJavaMode(), sheet2);
        //需要合并单元格
        writer.merge(5,20,1,1);

        //写第三个sheet包含多个table情况
        Sheet sheet3 = new Sheet(3, 0);
        sheet3.setSheetName("第三个sheet");
        Table table1 = new Table(1);
        table1.setHead(DataUtil.createTestListStringHead());
        writer.write1(DataUtil.createTestListObject(), sheet3, table1);

        //写sheet2  模型上打有表头的注解
        Table table2 = new Table(2);
        table2.setTableStyle(DataUtil.createTableStyle());
        table2.setClazz(WriteModel.class);
        writer.write(DataUtil.createTestListJavaMode(), sheet3, table2);

        writer.finish();
        out.close();
        return "OK";
    }

    @GetMapping("/writexls2temp")
    public String writexls2temp() throws Exception {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("temp.xlsx");
        OutputStream out = new FileOutputStream("/Users/yanshuai/Downloads/easyexcel-master2/src/test/resources/mmm.xlsx");

        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream,out,ExcelTypeEnum.XLSX,true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");
        sheet1.setStartRow(1);

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(DataUtil.createTestListObject(), sheet1);


        writer.finish();
        out.close();

        return "OK";
    }

    @GetMapping("/down")
    public void cooperation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        .getBytes(), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);

        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");
        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(DataUtil.createTestListStringHead());
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(DataUtil.createTestListObject(), sheet1);

        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(2, 3, WriteModel.class, "第二个sheet", null);
        sheet2.setTableStyle(DataUtil.createTableStyle());
        writer.write(DataUtil.createTestListJavaMode(), sheet2);

        //写第三个sheet包含多个table情况
        Sheet sheet3 = new Sheet(3, 0);
        sheet3.setSheetName("第三个sheet");
        Table table1 = new Table(1);
        table1.setHead(DataUtil.createTestListStringHead());
        writer.write1(DataUtil.createTestListObject(), sheet3, table1);

        //写sheet2  模型上打有表头的注解
        Table table2 = new Table(2);
        table2.setTableStyle(DataUtil.createTableStyle());
        table2.setClazz(WriteModel.class);
        writer.write(DataUtil.createTestListJavaMode(), sheet3, table2);

        //关闭资源
        writer.finish();
        out.close();
    }



}
