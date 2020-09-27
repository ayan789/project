package com.ccic.salesapp.performance.exception;

import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salesapp.performance.config.ErrorCode;

public class PlatformUserException extends PlatformBaseException {

    public PlatformUserException(ErrorCode errorCode) {
        super(errorCode.getError(), errorCode.getCode());
    }
}
