package com.neo;

import lombok.Data;

import java.util.Locale;

@Data
public class VMPageRequest {

    String pageId;
    Object content;
    Locale locale;
    Integer age;//被保人年龄
    boolean disabledAll = false;//所有不可编辑

    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }
        return LoginUtil.currentLocale();
    }

    public static VMPageRequest obtain(String pageId, Object content) {
        VMPageRequest vmPageRequest = new VMPageRequest();
        vmPageRequest.pageId = pageId;
        vmPageRequest.content = content;
        return vmPageRequest;
    }

}
