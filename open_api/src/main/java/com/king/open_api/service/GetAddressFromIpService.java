package com.king.open_api.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.king.open_api.entity.IPEntryCN;
import org.springframework.stereotype.Service;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 04:00
 * @description:
 */
@Service
public class GetAddressFromIpService {

    private static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    private static final String IP_URL_TaoBao = "https://ip.taobao.com/outGetIpInfo?accessKey=alibaba-inc&ip=%s";
    private static final String IP_URL_TEST = "http://ip-api.com/json/IP?lang=zh-CN";

    //百度IP地址查询接口
    private static final String IP_URL_BAIDU = "http://opendata.baidu.com/api.php?query=IP&co=&resource_id=6006&oe=utf8";


    public IPEntryCN getAddressFromIp(String ip) {
        //替换IP_URL_TEST中的IP为客户端传入的IP
        String url = IP_URL_TEST.replace("IP", ip);
        String result = HttpUtil.get(url);
        return JSON.parseObject(result, IPEntryCN.class);
    }

    public IPEntryCN getAddressFromIpTaoBao(String ip) {
        String url = String.format(IP_URL_TaoBao, ip);
        String result = HttpUtil.get(url);
        System.out.println(result);
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new GetAddressFromIpService().getAddressFromIpTaoBao("58.39.97.32"));
    }

    /**
     * 纯真IP地址查询接口
     *
     * @param ip
     * @return
     */
    public IPEntryCN getAddressFromIpCz88(String ip) {
        IPSeekerService ipSeekerService = IPSeekerService.getInstance();
        IPEntryCN ipEntryCN = new IPEntryCN();
        ipEntryCN.setQuery(ip);
        ipEntryCN.setCountry(ipSeekerService.getCountry(ip));
        ipEntryCN.setCity(ipSeekerService.getArea(ip));
        ipEntryCN.setRegion(ipSeekerService.getAddress(ip));
        return ipEntryCN;
    }


}