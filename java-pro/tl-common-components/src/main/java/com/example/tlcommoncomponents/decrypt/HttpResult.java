package com.example.tlcommoncomponents.decrypt;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Data;

@Data
public class HttpResult<T> implements Serializable {
    public final static String SUCCESS="1";
    public final static String MESSAGE="SUCCESS";
    private static final long serialVersionUID = 8496976140520997630L;
    private String code = SUCCESS;
    private T data;
    private String msg=MESSAGE;

    private HttpResult() {}

    public static <T> HttpResult success(T data) {
        HttpResult result = new HttpResult();
        result.setData(data);
        return result;
    }

    public static <T> HttpResult success() {
        HttpResult result = new HttpResult();
        result.setData(new HashMap<>());
        return new HttpResult();
    }
    
    public static <T> HttpResult success(int code, String msg) {
        HttpResult result = new HttpResult();
        result.setCode(code+"");
        result.setMsg(msg);
        return result;
    }

    public static <T> HttpResult success(T data,int code, String msg) {
        HttpResult result = new HttpResult();
        result.setData(data);
        result.setCode(code+"");
        result.setMsg(msg);
        return result;
    }
    
    public static <T> HttpResult error(int code, String msg) {
        HttpResult result = new HttpResult();
        result.setCode(code+"");
        result.setMsg(msg);
        return result;
    }

    public static <T> HttpResult error(T data,int code, String msg) {
        HttpResult result = new HttpResult();
        result.setData(data);
        result.setCode(code+"");
        result.setMsg(msg);
        return result;
    }
}