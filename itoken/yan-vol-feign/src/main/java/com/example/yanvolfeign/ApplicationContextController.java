package com.example.yanvolfeign;

import com.cxytiandi.encrypt.springboot.HttpMethodTypePrefixConstant;
import com.cxytiandi.encrypt.springboot.annotation.Decrypt;
import com.cxytiandi.encrypt.springboot.annotation.DecryptIgnore;
import com.cxytiandi.encrypt.springboot.annotation.Encrypt;
import com.cxytiandi.encrypt.springboot.annotation.EncryptIgnore;
import com.cxytiandi.encrypt.springboot.init.ApiEncryptDataInit;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationContextController  implements ApplicationContextAware {

    private String contextPath;

    private String getApiUri(Class<?> clz, Method method) {
        String methodType = "";
        StringBuilder uri = new StringBuilder();

        RequestMapping reqMapping = AnnotationUtils.findAnnotation(clz, RequestMapping.class);
        if (reqMapping != null) {
            uri.append(formatUri(reqMapping.value()[0]));
        }

        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
        DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);

        if (getMapping != null) {
            methodType = HttpMethodTypePrefixConstant.GET;
            uri.append(formatUri(getMapping.value()[0]));

        } else if (postMapping != null) {
            methodType = HttpMethodTypePrefixConstant.POST;
            uri.append(formatUri(postMapping.value()[0]));

        } else if (putMapping != null) {
            methodType = HttpMethodTypePrefixConstant.PUT;
            uri.append(formatUri(putMapping.value()[0]));

        } else if (deleteMapping != null) {
            methodType = HttpMethodTypePrefixConstant.DELETE;
            uri.append(formatUri(deleteMapping.value()[0]));

        } else if (requestMapping != null) {
            RequestMethod m = requestMapping.method()[0];
            methodType = m.name().toLowerCase() + ":";
            uri.append(formatUri(requestMapping.value()[0]));

        }

        if (StringUtils.hasText(this.contextPath)) {
            return methodType + this.contextPath + uri.toString();
        }

        return methodType + uri.toString();
    }

    private String formatUri(String uri) {
        if (uri.startsWith("/")) {
            if(uri.indexOf("{")!=-1)
                return uri.substring(0,uri.indexOf("{"));
            else
                return uri;
        }
        return null;
//        return "/" + uri;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.contextPath = ctx.getEnvironment().getProperty("com.example.yanvolfeign");
        Map<String, Object> beanMap = ctx.getBeansWithAnnotation(RestController.class);
//        System.out.println(JSONObject.toJSONString(beanMap));
        if (beanMap != null) {
            for (Object bean : beanMap.values()) {
                Class<?> clz = bean.getClass();
                Method[] methods = clz.getMethods();
                for (Method method : methods) {
                    Encrypt encrypt = AnnotationUtils.findAnnotation(method, Encrypt.class);
                    if (encrypt != null) {
                        // 注解中的URI优先级高
                        String uri = encrypt.value();
                        if (!StringUtils.hasText(uri)) {
                            uri = getApiUri(clz, method);
                        }

                        System.out.println("Encrypt URIIIII: {}"+uri);
                        ApiEncryptDataInit.responseEncryptUriList.add(uri);



                    }
                    Decrypt decrypt = AnnotationUtils.findAnnotation(method, Decrypt.class);
                    if (decrypt != null) {
                        String uri = decrypt.value();
                        if (!StringUtils.hasText(uri)) {
                            uri = getApiUri(clz, method);
                        }
                        System.out.println("Decrypt URI: {}"+uri);
                        ApiEncryptDataInit.requestDecyptUriList.add(uri);

                    }
                    EncryptIgnore encryptIgnore = AnnotationUtils.findAnnotation(method, EncryptIgnore.class);
                    if (encryptIgnore != null) {
                        // 注解中的URI优先级高
                        String uri = encryptIgnore.value();
                        if (!StringUtils.hasText(uri)) {
                            uri = getApiUri(clz, method);
                        }
                        System.out.println("EncryptIgnore URI: {}"+uri);
                        ApiEncryptDataInit.responseEncryptUriIgnoreList.add(uri);

                    }
                    DecryptIgnore decryptIgnore = AnnotationUtils.findAnnotation(method, DecryptIgnore.class);
                    if (decryptIgnore != null) {
                        String uri = decryptIgnore.value();
                        if (!StringUtils.hasText(uri)) {
                            uri = getApiUri(clz, method);
                        }
                        System.out.println("DecryptIgnore URI: {}"+uri);
                        ApiEncryptDataInit.requestDecyptUriIgnoreList.add(uri);
                    }
                }
            }
        }
    }



}
