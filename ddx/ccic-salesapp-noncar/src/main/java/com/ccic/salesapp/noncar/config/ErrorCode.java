package com.ccic.salesapp.noncar.config;

/**
 * 定义你的错误码和错误信息
 */
public enum ErrorCode {

    TEST_ERROR(10001,"这里定义你的错误信息")

    ;


    private String error;
    private int code;

    private ErrorCode(int code,String error){
        this.code=code;
        this.error=error;
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
