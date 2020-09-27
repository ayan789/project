
package com.ccic.salessapp.common.core.web;

import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.exception.handler.db.SysErrorInfo;
import com.ccic.salessapp.common.core.exception.handler.db.SysErrorInfoService;
import com.ccic.salessapp.common.core.logger.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;

@ResponseBody
@Controller
@Slf4j
public class PlatformErrorController extends AbstractErrorController {
    private ErrorAttributes attributes;
    public PlatformErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.attributes=errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "error",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpResult error(ServletWebRequest webRequest, HttpServletResponse httpServletResponse, Exception ex) {
        HttpStatus status = HttpStatus.valueOf(httpServletResponse.getStatus());
        Throwable throwable=attributes.getError(webRequest);
        PlatformBaseException exception=findBaseException(null==throwable?ex:(Exception) throwable,status,webRequest);
        return HttpResult.error(exception.getCode(), exception.getMessage());
    }


    @Autowired
    SysErrorInfoService service;
    private PlatformBaseException findBaseException(Exception ex,HttpStatus status,ServletWebRequest request){
        PlatformBaseException baseException=(ex.getCause() instanceof PlatformBaseException)?(PlatformBaseException)ex.getCause():(ex instanceof PlatformBaseException)?(PlatformBaseException)ex: new
                PlatformBaseException("系统繁忙,请稍后再试",99999);
        String requestUri=getAttribute(request,"javax.servlet.error.request_uri");
        log.error("requestUri:{},message:{},status:{},errorCode:{}", requestUri,ex.getMessage(),status.value(),baseException.getCode());
//        log.error(ex.getMessage(),ex);
        service.put(new SysErrorInfo(status,ex,String.valueOf(baseException.getCode()),baseException.getMessage(),requestUri,Logger.LocalLogger.get()));
        return baseException;
    }

    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
