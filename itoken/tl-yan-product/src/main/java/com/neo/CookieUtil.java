package com.neo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * cookie工具类
 *
 *add by qisheng 2017-04-23
 */
public class CookieUtil {

    public static String getSessionId(HttpServletRequest request) {
        return getValueByName(request, Constant.COOKIE_SESSION_ID);
    }

    public static void setSessionId(HttpServletResponse response, int expiry, String domain) {
        addCookie(response, Constant.COOKIE_SESSION_ID, UUID.randomUUID().toString(), expiry, domain);
    }

    /**
     * 获得COOKIE
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie;
    }

    /**
     * 获得COOKIE里的值
     *
     * @param request
     * @param name
     * @return
     */
    public static String getValueByName(HttpServletRequest request, String name) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie != null ? cookie.getValue() : "";
    }

    /**
     * 存储cookie值
     *
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @param domain
     * @return
     */
    public static Cookie addCookie(HttpServletResponse response, String name, String value, Integer expiry,
                                   String domain, String path) {

        return addCookie(response,name,value,expiry,domain,path,true);
    }

    public static Cookie addCookie(HttpServletResponse response, String name, String value, Integer expiry,
                                   String domain, String path,Boolean isSecure) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        cookie.setPath(StringUtils.isBlank(path) ? "/" : path);

        if(isSecure){
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
        }
        response.addCookie(cookie);
        return cookie;
    }



    /**
     * 将cookie保存在根目录(指定销毁时间)。
     *
     * @param response
     * @param name
     * @param value
     * @param expiry 过期时间
     * @param domain
     * @return
     */
    public static Cookie addCookie(HttpServletResponse response, String name, String value, Integer expiry,
                                   String domain) {
        return addCookie(response, name, value, null, domain, null);
    }

    /**
     * 将cookie保存在根目录(浏览器关闭销毁)。
     *
     * @param response
     * @param name
     * @param value
     * @param domain
     * @return
     */
    public static Cookie addCookie(HttpServletResponse response, String name, String value, String domain) {
        return addCookie(response, name, value, null, domain, null);
    }

    /**
     * 取消cookie
     *
     * @param request
     * @param response
     * @param name
     * @param domain
     */
    public static void cancleCookie(HttpServletRequest request, HttpServletResponse response, String name,
                                    String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }
}
