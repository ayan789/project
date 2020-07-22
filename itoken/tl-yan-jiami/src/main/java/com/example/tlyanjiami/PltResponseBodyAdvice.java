package com.example.tlyanjiami;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class PltResponseBodyAdvice implements ResponseBodyAdvice {
    private final Gson gson = new Gson();
    @Autowired
    PlatformFilterControl control;

    @Override
    public boolean supports(MethodParameter parameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter parameter, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest req = RequestContext.getServletRequest();
        String requestUri = req.getRequestURI();
        //String accessId = req.getHeader(CoreContants.X_ACCESS_ID);
        boolean shouldEncrypt = shouldEncrypt(requestUri);
        log.info("requestUri:{},shouldEncrypt:{}", requestUri, shouldEncrypt);
        boolean EncryptSwitch = null == control || control.encryptSwitch();
        String inner = req.getHeader(CoreContants.X_INNER_SERVICE);
        boolean isInner = StringUtils.isNotBlank(inner) && Boolean.valueOf(inner);
        shouldEncrypt = true;
        EncryptSwitch = true;
        if (shouldEncrypt && EncryptSwitch && !isInner) {
            HttpResult result = (HttpResult) o;
            if (result != null && StringUtils.equals("1", result.getCode())) {
                //String keyInfo = redisService.getAccessId(accessId);
//                String staticKey=AESUtil.key();
            	String staticKey="k4Ea3T650sPdUdM5";
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
        List<String> strings = null != control ? control.notEncryptUri() : Lists.newArrayList();
        Optional<List<String>> optional=Optional.ofNullable(strings);
        List<String> list=optional.orElse(Lists.newArrayList());
        return !(list.contains(requestUri)
                || StringUtils.startsWith(requestUri, "/actuator")
                || StringUtils.startsWith(requestUri, "/webjars")
                || StringUtils.startsWith(requestUri, "/swagger-resources"));
    }
}
