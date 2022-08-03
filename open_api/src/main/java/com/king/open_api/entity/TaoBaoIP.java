package com.king.open_api.entity;

import lombok.Data;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年08月03日 16:46
 * @description:
 */
@Data
public class TaoBaoIP {
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String isp;
    private String country_id;
    private String area_id;
    private String region_id;
    private String city_id;
    private String county_id;
    private String isp_id;
}