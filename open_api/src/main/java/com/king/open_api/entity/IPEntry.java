package com.king.open_api.entity;

import lombok.Data;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 03:38
 * @description: IP地址信息
 */
@Data
public class IPEntry {
    public String beginIp;
    public String endIp;
    public String country;
    public String area;

    /**
     * 构造函数
     */
    public IPEntry() {
        beginIp = endIp = country = area = "";
    }

    public String toString() {
        return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-" + this.endIp;
    }
}