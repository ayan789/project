package com.ccic.salesapp.noncar.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ccic.salesapp.noncar.config.ErrorCode;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.utils.StringUtils;

public class PlatformNoncarException extends PlatformBaseException {

	
    public PlatformNoncarException(ErrorCode errorCode) {
        super(errorCode.getError(), errorCode.getCode());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestId = (String) request.getAttribute("requestId");
        if(errorCode!= null && !StringUtils.isBlank(errorCode.getError())) {
        	errorCode.setError("["+requestId+"]:"+errorCode.getError());
        }
    }
}
