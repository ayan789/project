package com.ccic.salessapp.common.core.filter;

import com.ccic.salessapp.common.core.CoreContants;
import com.ccic.salessapp.common.core.CoreErrorCode;
import com.ccic.salessapp.common.core.advice.PltResponseBodyAdvice;
import com.ccic.salessapp.common.core.exception.PlatformCoreException;
import com.ccic.salessapp.common.core.filter.extend.db.PlatformFilterDbControl;
import com.ccic.salessapp.common.core.logger.Logger;
import com.ccic.salessapp.common.core.support.RequestContext;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.service.RedisService;
import com.ccic.salessapp.common.utils.TokenUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Order(3)
public class LoginFilter extends OncePerRequestFilter {
    private final static Logger log=Logger.getLogger(LoginFilter.class);
    private final Gson gson = new Gson();
    @Autowired
    PlatformFilterDbControl control;
    @Autowired
    RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean shouldLogin = shouldLogin(request.getRequestURI());
        boolean loginSwitch = control.loginSwitch();
        if (shouldLogin && loginSwitch) from(request);
        filterChain.doFilter(request, response);
    }

    private boolean shouldLogin(String requestUri) {
        return !(control.notLoginUri(requestUri));
    }

    private void checkUser(HttpServletRequest request, UserToken accessToken) {
        String contextType = request.getContentType();
        String userCode = "";
        if (StringUtils.contains(contextType, MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, Object> data = gson.fromJson(RequestContext.getBody(), Map.class);
            if (data != null) {
                userCode = (String) data.get("userCode");
            }
        } else if (StringUtils.contains(contextType, MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            String[] strings = request.getParameterValues("userCode");
            if (!ObjectUtils.isEmpty(strings)) userCode = strings[0];
        } else if (StringUtils.contains(contextType, MediaType.MULTIPART_FORM_DATA_VALUE)) {
            return;
        }
        log.info("login userCode {},token userCode {}", userCode, accessToken.getUserCode());
        if (StringUtils.equals(userCode, accessToken.getUserCode())) return;
        throw new PlatformCoreException(CoreErrorCode.NOT_LOGIN);
    }


    private void from(HttpServletRequest request) {
        UserToken accessToken = TokenUtil.getToken();
        if (accessToken == null) throw new PlatformCoreException(CoreErrorCode.NOT_LOGIN);
        checkUser(request, accessToken);
        String accessKey = request.getHeader(CoreContants.X_ACCESS_ID);
        String keyInfo = redisService.getAccessId(accessKey);
        if (StringUtils.isNotBlank(keyInfo))
            redisService.putAccessId(accessKey, keyInfo, 7 * 24 * 60 * 60);
        String token = RequestContext.getHeader(CoreContants.X_ACCESS_TOKEN);
        redisService.putAccessToken(token, accessToken, 7 * 24 * 60 * 60);
    }
}
