package com.ccic.salessapp.common.core.exception.handler.db;

import com.ccic.salessapp.common.utils.PropertiesUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class SysErrorInfo {
    private HttpStatus httpStatus;
    private Exception exception;
    private String errorCode;
    private String errorMsg;
    private String requestUri;
    private String uuid;


    public Map<String,Object> trans(){
        Map<String,Object> data=new HashMap<>();
        data.put("serviceId",PropertiesUtil.getProperty("spring.application.name","UnknownSource"));
        data.put("httpStatus",httpStatus.value());
        data.put("httpStatusMsg",httpStatus.getReasonPhrase());
        data.put("errorCode",errorCode);
        data.put("errorMsg",errorMsg);
        data.put("exceptionInfo",getErrorInfo(exception));
        data.put("exceptionClass",exception.getClass().getName());
        data.put("exceptionMsg",exception.getMessage());
        data.put("requestUri",requestUri);
        data.put("requestId",uuid);
        return data;
    }



    String getErrorInfo(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}
