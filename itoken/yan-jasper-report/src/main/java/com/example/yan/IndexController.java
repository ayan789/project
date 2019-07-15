package com.example.yan;


import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.json.JRJsonNode;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class IndexController extends HttpServlet {
    @GetMapping("/home")
    public String index(){
        return "up jasper report";
    }

    @GetMapping("/pdf")
    public void pdf(){
        try
        {
            File reportFile = new File("/Users/yanshuai/Downloads/BOOT-INF/report6.jasper");
            File reportPdfFile = new File("/Users/yanshuai/Downloads/BOOT-INF/report6.pdf");
            Map<String,Object> parameters=new HashMap<String,Object>();
            JRBeanCollectionDataSource resultsList = new JRBeanCollectionDataSource(UserSvi.createUsers());
            parameters.put("resultsList", resultsList);
            parameters.put("reportTitle", "This is a test report");
            try {
                byte[] reportStream = JasperRunManager.runReportToPdf(reportFile.getPath(),parameters,resultsList);
                FileOutputStream fw = new FileOutputStream(reportPdfFile);
                fw.write(reportStream);
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @GetMapping("/pdf2")
    public void pdf2(){
        try
        {
            File reportFile = new File("/Users/yanshuai/Downloads/BOOT-INF/report12.jasper");
            File reportPdfFile = new File("/Users/yanshuai/Downloads/BOOT-INF/report12.pdf");
            Map<String,Object> parameters=new HashMap<String,Object>();
            Data data1 = new Data();
            data1.setAa("www");
            data1.setBb("rrr");

            JSONObject jsonObject = JSONObject.fromObject(data1);
            String json = jsonObject.toString();

            Map<String, Object> jasperPara = new HashMap<String, Object>();

            InputStream is = new ByteArrayInputStream(json.getBytes());
            jasperPara.put("data", is);
            jasperPara.put(JsonQueryExecuterFactory.JSON_LOCALE, Locale.CHINA);
            jasperPara.put(JRParameter.REPORT_LOCALE, Locale.CHINA);

            try {
                byte[] reportStream = JasperRunManager.runReportToPdf(reportFile.getPath(),jasperPara);
                FileOutputStream fw = new FileOutputStream(reportPdfFile);
                fw.write(reportStream);
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}