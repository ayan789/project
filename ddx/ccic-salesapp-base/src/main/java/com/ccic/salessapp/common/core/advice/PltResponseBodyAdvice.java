package com.ccic.salessapp.common.core.advice;

import com.ccic.salessapp.common.core.CoreContants;
import com.ccic.salessapp.common.core.CoreErrorCode;
import com.ccic.salessapp.common.core.filter.extend.PlatformFilterControl;
import com.ccic.salessapp.common.core.filter.extend.db.PlatformFilterDbControl;
import com.ccic.salessapp.common.core.logger.Logger;
import com.ccic.salessapp.common.core.support.RequestContext;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.service.RedisService;
import com.ccic.salessapp.common.utils.AESUtil;
import com.google.common.collect.Lists;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class PltResponseBodyAdvice implements ResponseBodyAdvice {
    private final static Logger log=Logger.getLogger(PltResponseBodyAdvice.class);
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @Autowired
    RedisService redisService;
    @Autowired
    PlatformFilterDbControl control;

    @Override
    public boolean supports(MethodParameter parameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter parameter, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest req = RequestContext.getServletRequest();
        String requestUri = req.getRequestURI();
        boolean shouldEncrypt = shouldEncrypt(requestUri);
        boolean EncryptSwitch = control.encryptSwitch();
        String inner = req.getHeader(CoreContants.X_INNER_SERVICE);
        boolean isInner = StringUtils.isNotBlank(inner) && Boolean.valueOf(inner);
        boolean isEncrypt=shouldEncrypt && EncryptSwitch && !isInner;
        log.info("requestUri:{},shouldEncrypt:{},encryptSwitch:{},isInner:{},isEncrypt:{}", requestUri, shouldEncrypt,EncryptSwitch,isInner,isEncrypt);
        if (isEncrypt) {
            HttpResult result = (HttpResult) o;
            if (result != null && StringUtils.equals("1", result.getCode())) {
                //String keyInfo = redisService.getAccessId(accessId);
                String staticKey=AESUtil.key();
                try {
                    Object data = result.getData();
                    if (data == null) return result;
                    String encData = AESUtil.encrypt((data instanceof String) ? (String) data : gson.toJson(data), staticKey);
                    return HttpResult.success(encData);
                } catch (Exception e) {
                    log.error("response encrypt error,requestUri:{}", requestUri);
                    CoreErrorCode code=CoreErrorCode.RESPONSE_ERROR;
                    return HttpResult.error(code.getCode(), code.getError());
                }
            }
        }
        RequestContext.removeBody();
        return o;
    }

    private boolean shouldEncrypt(String requestUri) {
        return !(control.notEncryptUri(requestUri));
    }
}
