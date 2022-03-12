package com.king.security.util;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-12 06:31
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.king.security.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserIPUtil {

    //获取当前用户的用户名的ID
    public static Long getCurrentUserId() {
        return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    //获取当前用户的用户姓名
    public static String getCurrentUserName() {
        return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
    }

    //获取当前用户的登录名
    public static String getCurrentUserUserName() {
        return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    //获取IP
    public static String getIPAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "未获取到IP";
        }
        return ipAddress;
    }
}
