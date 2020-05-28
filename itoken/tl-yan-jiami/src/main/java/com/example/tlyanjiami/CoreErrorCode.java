package com.example.tlyanjiami;

public enum CoreErrorCode {
    NOT_LOGIN(10000,"用户未登录或会话超时"),
    MISSING_ACCESS_ID(10001,"缺失参数X-Access-Id"),
    PARAMETER_ERROR(10002,"统一参数不合法异常"),
    DECRYPT_REQUEST_ERROR(10003,"请求参数解密失败"),
    RESPONSE_ERROR(10004,"数据响应失败"),
    ;


    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    CoreErrorCode(int code, String error) {
        this.code = code;
        this.error = error;
    }



}
