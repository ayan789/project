package com.example.tlyanencrypt.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxytiandi.encrypt.springboot.HttpMethodTypePrefixConstant;
import com.cxytiandi.encrypt.springboot.annotation.*;
import com.cxytiandi.encrypt.springboot.init.ApiEncryptDataInit;
import com.example.tlyanencrypt.R;
import com.example.tlyanencrypt.dto.BookDto;
import com.example.tlyanencrypt.dto.Insure;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BookController implements ApplicationContextAware {

    private String contextPath;

    /**
     * 需要对响应内容进行加密的接口URI<br>
     * 比如：/user/list<br>
     * 不支持@PathVariable格式的URI
     */
    public static List<String> responseEncryptUriList = new ArrayList<String>();

    /**
     * 需要对请求内容进行解密的接口URI<br>
     * 比如：/user/list<br>
     * 不支持@PathVariable格式的URI
     */
    public static List<String> requestDecyptUriList = new ArrayList<String>();

    /**
     * 忽略加密的接口URI<br>
     * 比如：/user/list<br>
     * 不支持@PathVariable格式的URI
     */
    public static List<String> responseEncryptUriIgnoreList = new ArrayList<String>();

    /**
     * 忽略对请求内容进行解密的接口URI<br>
     * 比如：/user/list<br>
     * 不支持@PathVariable格式的URI
     */
    public static List<String> requestDecyptUriIgnoreList = new ArrayList<String>();


//    @Encrypt
//    @GetMapping("/book/{id}")
//    public BookDto book1(@PathVariable("id") Integer id) {
//        BookDto bookDto = new BookDto();
//        bookDto.setId(1);
//        bookDto.setName("ABC");
//        bookDto.setPrice(199.99);
//        bookDto.setCreateTime(new Date());
//        Insure insure = new Insure();
//        insure.setRider("附加险");
//        bookDto.setInsure(insure);
//        String abc = JSONObject.toJSONString(bookDto);
//        System.out.println(abc);
//
//        System.out.println(bookDto.getId() + "\t" + bookDto.getName());
//        System.out.println(bookDto.getCreateTime());
//        System.out.println(bookDto.getPrice());
//        System.out.println("GetMapping");
//        System.out.println("id:" + id);
//        System.out.println("rider:" + bookDto.getInsure().getRider());
//        return bookDto;
//    }


//    @EnableQazSecurity
//    @PostMapping("/book/{id}")
//    public BookDto book2(@RequestBody BookDto dto, @PathVariable("id") Integer id) {
//        System.out.println(dto.getId() + "\t" + dto.getName());
//        System.out.println(dto.getCreateTime());
//        System.out.println(dto.getPrice());
//        System.out.println("PostMapping");
//        System.out.println("id:" + id);
//        System.out.println("rider:" + dto.getInsure().getRider());
//        return dto;
//    }


    @EnableQazSecurity
    @PutMapping("/book/{id}")
    public R book3(@RequestBody BookDto dto, @PathVariable("id") Integer id) {
        System.out.println("vvvvvvvvvg");
        System.out.println(dto.getId() + "\t" + dto.getName());
        System.out.println(dto.getCreateTime());
        System.out.println(dto.getPrice());
        System.out.println("PutMapping");
        System.out.println("id:" + id);
        System.out.println("rider:" + dto.getInsure().getRider());
        return R.ok().data(dto);
    }


    @Encrypt
    @Decrypt
    @PutMapping("/book/abc/{id}")
    public R bookabc3(@RequestBody BookDto dto, @PathVariable("id") Integer id,@RequestParam(value = "name", required = false)String name,@RequestParam(value = "qaz", required = false)Integer qaz) {
        System.out.println(name);
        System.out.println(qaz);
        System.out.println(dto.getId() + "\t" + dto.getName());
        System.out.println(dto.getCreateTime());
        System.out.println(dto.getPrice());
        System.out.println("PutMapping");
        System.out.println("id:" + id);
        System.out.println("rider:" + dto.getInsure().getRider());
        return R.ok().data(dto);
    }




//    @PutMapping("/book/abc/{id}/{name}")
//    public R bookabc44(@RequestBody BookDto dto, @PathVariable("id") Integer id,@PathVariable("name") String name) {
//        System.out.println(dto.getId() + "\t" + dto.getName());
//        System.out.println(dto.getCreateTime());
//        System.out.println(dto.getPrice());
//        System.out.println("PutMapping");
//        System.out.println("id:" + id);
//        System.out.println("rider:" + dto.getInsure().getRider());
//        return R.ok().data(dto);
//    }

//    @PutMapping("/book3/def")
//    public R bookdfe3(@RequestBody BookDto dto, @PathVariable("id") Integer id) {
//        System.out.println(dto.getId() + "\t" + dto.getName());
//        System.out.println(dto.getCreateTime());
//        System.out.println(dto.getPrice());
//        System.out.println("PutMapping");
//        System.out.println("id:" + id);
//        System.out.println("rider:" + dto.getInsure().getRider());
//
//        return R.ok().data(dto);
//    }

//    @DeleteMapping("/book/{id}")
//    public BookDto book4(@RequestBody BookDto dto, @PathVariable("id") Integer id) {
//        System.out.println(dto.getId() + "\t" + dto.getName());
//        System.out.println(dto.getCreateTime());
//        System.out.println(dto.getPrice());
//        System.out.println("DeleteMapping");
//        System.out.println("id:" + id);
//        System.out.println("rider:" + dto.getInsure().getRider());
//        return dto;
//    }

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
        this.contextPath = ctx.getEnvironment().getProperty("com.example.tlyanencrypt");
        Map<String, Object> beanMap = ctx.getBeansWithAnnotation(RestController.class);
        System.out.println(JSONObject.toJSONString(beanMap));
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
            System.out.println(JSONObject.toJSONString("-----------k000"));
            System.out.println(JSONObject.toJSONString(ApiEncryptDataInit.responseEncryptUriList.stream().distinct().collect(Collectors.toList())));
            System.out.println(JSONObject.toJSONString(ApiEncryptDataInit.requestDecyptUriList.stream().distinct().collect(Collectors.toList())));
            System.out.println(JSONObject.toJSONString(ApiEncryptDataInit.responseEncryptUriIgnoreList.stream().distinct().collect(Collectors.toList())));
            System.out.println(JSONObject.toJSONString(ApiEncryptDataInit.requestDecyptUriIgnoreList.stream().distinct().collect(Collectors.toList())));
//          ApiEncryptDataInit.responseEncryptUriList(responseEncryptUriList);
        }
    }

    public static void main(String[] args) {
        String str = "/book/abc/{123}";
        System.out.println(str.substring(0,str.indexOf("{")-1));
    }

    //都没有   请求明文 返回明文
    //Encrypt 请求明文 返回密文
    //Decrypt 请求密文 返回明文


}
