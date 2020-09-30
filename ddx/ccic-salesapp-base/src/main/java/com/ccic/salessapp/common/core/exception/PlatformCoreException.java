package com.ccic.salessapp.common.core.exception;

import com.ccic.salessapp.common.core.CoreErrorCode;

public class PlatformCoreException extends PlatformBaseException{

    private static final long serialVersionUID = -381641858182209119L;


    public PlatformCoreException(CoreErrorCode errorCode) {
        super(errorCode.getError(),errorCode.getCode());
    }

    public PlatformCoreException(String error, int code) {
        super(error, code);
    }
}