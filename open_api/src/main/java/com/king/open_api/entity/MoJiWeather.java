package com.king.open_api.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 11:14
 * @description:
 */
@Data
public class MoJiWeather {
    private Integer code;
    private String msg;
    private WeatherData data;

    @Data
    public static class WeatherData {
        private Integer total;
        private String sourceName;
        private String logoUrl;
        private List<Weather> list;
    }

    @Data
    public static class Weather {
        private String city;
        private String lastUpdateTime;
        private String date;
        private String weather;
        private String temp;
        private String humidity;
        private String wind;
        private String pm25;
        private String pm10;
        private String low;
        private String high;
        private String airData;
        private String airQuality;
        private long dateLong;
        private int weatherType;
        private int windLevel;
        private String province;
        private String moreData;
    }

    @Data
    public static class MoreData {
        private String sunrise;
        private String sunset;
        private String precipitation;
        private List<Alert> alert;
    }

    @Data
    public static class Alert {
        private String update_time;
        private int infoid;
        private String level;
        private String pub_time;
        private String name;
        private String title;
        private String type;
        private String content;
    }
}