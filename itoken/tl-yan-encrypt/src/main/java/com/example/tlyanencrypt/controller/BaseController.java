package com.example.tlyanencrypt.controller;

import com.example.tlyanencrypt.R;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {
//    private ThreadLocal<Map<String,Object>> datas = new ThreadLocal<>();

    private ThreadLocal<Map<String,Object>> datas = new ThreadLocal<>();


    protected void start(){
        Map<String,Object> resultMap = new HashMap<>();
        datas.set(resultMap);
    }

    protected Object end() {
        Map<String, Object> resultMap = datas.get();
        datas.remove();
        return resultMap;
    }

    public void success(boolean flg){
        Map<String,Object> resultMap = datas.get();
        resultMap.put("success",flg);
    }

    public void params(String key ,Object value){
        Map<String,Object> resultMap = datas.get();
        resultMap.put(key,value);
    }

}
