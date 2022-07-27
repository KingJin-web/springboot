package com.king.open_api.entity;

import lombok.Data;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 04:10
 * @description:
 */
@Data
public class IPEntryCN {


//"status": "success",
//        "country": "中国",
//        "countryCode": "CN",
//        "region": "HN",
//        "regionName": "湖南",
//        "city": "常德",
//        "zip": "",
//        "lat": 29.0354,
//        "lon": 111.6971,
//        "timezone": "Asia/Shanghai",
//        "isp": "China Mobile communications corporation",
//        "org": "China Mobile",
//        "as": "AS56047 China Mobile communications corporation",
//        "query": "120.227.93.113"

    //状态
    private String status;
    //国家
    private String country;
    //国家编码
    private String countryCode;
    //省份
    private String region;
    //省份名称
    private String regionName;
    //城市
    private String city;
    //邮编
    private String zip;
    //纬度
    private String lat;
    //经度
    private String lon;
    //时区
    private String timezone;
    //运营商
    private String isp;
    //运营商名称
    private String org;
    //AS号
    private String as;
    //查询的IP
    private String query;


}