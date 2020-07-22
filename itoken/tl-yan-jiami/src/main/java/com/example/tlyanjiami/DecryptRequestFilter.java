package com.example.tlyanjiami;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Order(1)
@Slf4j
public class DecryptRequestFilter extends OncePerRequestFilter {
    @Autowired
    PlatformFilterControl control;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        boolean shouldDecrypt = shouldDecrypt(requestUri);
        log.info("requestUri:{},shouldDecrypt:{}", requestUri, shouldDecrypt);
        PltHttpServletRequestWrapper wrapper = null;
        boolean decryptSwitch = null == control || control.decryptSwitch();
        String inner = request.getHeader(CoreContants.X_INNER_SERVICE);
        boolean isInner = StringUtils.isNotBlank(inner) && Boolean.valueOf(inner);
        log.info("requestUri:{},shouldDecrypt:{},DecryptSwitch:{},isInner:{}", requestUri, shouldDecrypt, decryptSwitch, isInner);
        shouldDecrypt = true;
        decryptSwitch = true;
        if (shouldDecrypt && decryptSwitch && !isInner) {
//            String keyInfo = accessId(request);
           // String staticKey=AESUtil.key();
        	 String staticKey="k4Ea3T650sPdUdM5";
            try {
                wrapper = new PltHttpServletRequestWrapper(request, staticKey);
            } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException e) {
                log.error("request decrypt error,requestUri:{}", requestUri);
                throw new PlatformCoreException(CoreErrorCode.DECRYPT_REQUEST_ERROR);
            }
        } else wrapper = new PltHttpServletRequestWrapper(request);
        filterChain.doFilter(wrapper, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String contextType = request.getContentType();
        List<String> uri=null==control?new ArrayList<>():control.specialUri();
        return (StringUtils.contains(contextType, MediaType.MULTIPART_FORM_DATA_VALUE)||(CollectionUtils.isNotEmpty(uri)&&uri.contains(request.getRequestURI())))||control
                .isSpecial(request.getRequestURI());
    }

    private boolean shouldDecrypt(String requestUri) {
        List<String> strings = null != control ? control.notDecryptUri() : Lists.newArrayList();
        Optional<List<String>> optional=Optional.ofNullable(strings);
        List<String> list=optional.orElse(Lists.newArrayList());
        return !(list.contains(requestUri)
                || StringUtils.startsWith(requestUri, "/actuator")
                || StringUtils.startsWith(requestUri, "/webjars")
                || StringUtils.startsWith(requestUri, "/swagger-resources"));
    }

    public class PltHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final String body;

        public PltHttpServletRequestWrapper(HttpServletRequest request, String key) throws IOException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
            super(request);
            String source = IOUtils.toString(request.getInputStream(), "UTF-8");
            this.body = StringUtils.isNotBlank(source) ? AESUtil.decrypt(source, key) : "";
            RequestContext.setBody(body);
        }

        public PltHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            String source = IOUtils.toString(request.getInputStream(), "UTF-8");
            this.body = source;
            RequestContext.setBody(body);
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(this.getInputStream()));
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(body.getBytes());
            ServletInputStream stream = new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {
                }

                @Override
                public int read() throws IOException {
                    return inputStream.read();
                }
            };
            return stream;
        }
    }


    String accessId(HttpServletRequest request) {
        String accessId = request.getHeader(CoreContants.X_ACCESS_ID);
        if (StringUtils.isBlank(accessId)) throw new PlatformCoreException(CoreErrorCode.MISSING_ACCESS_ID);
        String keyInfo = "";
        // redisService.getAccessId(accessId);
        if (StringUtils.isBlank(keyInfo)) throw new PlatformCoreException(CoreErrorCode.NOT_LOGIN);
        //redisService.putAccessId(accessId, keyInfo, 24* 60 * 60);
        return keyInfo;
    }
}
