package com.cxytiandi.encrypt.util;

public class BaseException extends Exception {
    private static String errorMessage;
    private static String errorCode;

    public BaseException(String errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static String getErrorCode() {
        return errorCode;
    }

    public static void setErrorCode(String errorCode) {
        BaseException.errorCode = errorCode;
    }

    public static void setErrorMessage(String errorMessage) {
        BaseException.errorMessage = errorMessage;
    }

}
