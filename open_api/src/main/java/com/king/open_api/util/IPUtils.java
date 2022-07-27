package com.king.open_api.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;

import javax.servlet.http.HttpServletRequest;
import java.net.*;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 04:24
 * @description:
 */
public class IPUtils {
    /**
     * @param ip
     * @return
     */
    public static String ipToLong(String ip) {
        String[] ips = ip.split("\\.");
        long ipLong = (Long.parseLong(ips[0]) << 24) | (Long.parseLong(ips[1]) << 16) | (Long.parseLong(ips[2]) << 8) | Long.parseLong(ips[3]);
        return String.valueOf(ipLong);
    }

    /**
     * @param ipLong
     * @return
     */
    public static String longToIp(long ipLong) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf((ipLong >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((ipLong & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((ipLong & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((ipLong & 0x000000FF)));
        return sb.toString();
    }
    /**
     * 获取本机公网IP
     * @return ip
     */
    public static String getLocalIp() {
        try {
            String url = "https://api.ipify.org/?format=json";
            return JSON.parseObject(HttpUtil.get(url)).get("ip").toString();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                String url = "http://httpbin.org/ip";
                return JSON.parseObject(HttpUtil.get(url)).get("origin").toString();
            }catch (Exception e1){
                e1.printStackTrace();
                try {
                    return InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }

    //从http中获取ip
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
               //如果是127。。。。。的话，就是本机的ip
                if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1") || ipAddress.equals("::1")) {
                    ipAddress = getLocalIp();
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


    public static void main(String[] args) {
        System.out.println(getLocalIp());
    }


}