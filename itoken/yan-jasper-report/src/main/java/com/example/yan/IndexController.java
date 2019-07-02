package com.example.yan;


import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
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
            File reportFile = new File("/Users/yanshuai/Downloads/BOOT-INF/report3.jasper");
            File reportPdfFile = new File("/Users/yanshuai/Downloads/BOOT-INF/report3.pdf");
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

}