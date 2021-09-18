package com.king.other.short_link.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-18 16:46
 */
public class GetRequestIp {
    public static String post(HttpServletRequest request, HttpServletResponse response) {
        return getIpAddress(request);
    }

    public static String getIpAddress(HttpServletRequest request) {

        String ip = null;
        ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for:" + ip);

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            System.out.println("request.getHeader(\"X-Forwarded-For\")=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("request.getHeader(\"Proxy-Client-IP\")=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("request.getHeader(\"WL-Proxy-Client-IP\")=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("request.getHeader(\"HTTP_CLIENT_IP\")=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("request.getHeader(\"HTTP_X_FORWARDED_FOR\")=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("request.getRemoteAddr()=" + ip);
        }

        if (null != ip && ip.indexOf(',') != -1) {
            //如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串 IP 值
            //取X-Forwarded-For中第一个非unknown的有效IP字符串
            //如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
            //用户真实IP为： 192.168.1.110
            //注意:当访问地址为 localhost 时 地址格式为 0:0:0:0:0:0:1
            System.out.println("ip=" + ip);
            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                if (null != ips[i] && !"unknown".equalsIgnoreCase(ips[i])) {
                    ip = ips[i];
                    break;
                }
            }
            if ("0:0:0:0:0:0:1".equals(ip)) {
                System.out.println("由于客户端访问地址使用 localhost，获取客户端真实IP地址错误，请使用IP方式访问");
            }
        }

        if ("unknown".equalsIgnoreCase(ip)) {
            System.out.println("由于客户端通过Squid反向代理软件访问，获取客户端真实IP地址错误，请更改squid.conf配置文件forwarded_for项默认是为on解决");
        }

        return ip;
    }
}