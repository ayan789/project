package com.common.util;


import javax.servlet.http.HttpServletRequest;

public class IpMessage {
    /**
     * <p>Title: getIpAddr</p>
     * <p>Description: 获取客户端IP</p>
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个非unknown的有效IP字符串
        if (ip != null && ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            for (String ip1 : ips) {
                if (!"unknown".equalsIgnoreCase(ip1.trim())) {
                    ip = ip1.trim();
                    break;
                }
            }
        }
        return ip;
    }
}
