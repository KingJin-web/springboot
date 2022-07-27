package com.king.open_api.vo;

import com.king.open_api.entity.MoJiWeather;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 11:49
 * @description:
 */
@Data
@ApiModel(value = "天气信息")
public class WeatherVo {
    @ApiModelProperty(value = "城市名称")
    private String city;
    @ApiModelProperty(value = "天气状况")
    private List<MoJiWeather.Weather> list;

    @Data
    public static class WeatherDataVo {
        @ApiModelProperty(value = "上次更新时间")
        private String lastUpdateTime;
        @ApiModelProperty(value = "日期")
        private String date;
        @ApiModelProperty(value = "天气")
        private String weather;
        @ApiModelProperty(value = "温度")
        private String temp;
        @ApiModelProperty(value = "湿度")
        private String humidity;
        @ApiModelProperty(value = "风力")
        private String wind;
        @ApiModelProperty(value = "PM2.5")
        private String pm25;
        @ApiModelProperty(value = "PM10")
        private String pm10;
        @ApiModelProperty(value = "最低温度")
        private String low;
        @ApiModelProperty(value = "最高温度")
        private String high;
        @ApiModelProperty(value = "空气质量")
        private String airData;
        @ApiModelProperty(value = "空气质量指数")
        private String airQuality;
        @ApiModelProperty(value = "日期long")
        private long dateLong;
        @ApiModelProperty(value = "天气类型")
        private int weatherType;
        @ApiModelProperty(value = "风力等级")
        private int windLevel;
        @ApiModelProperty(value = "省份")
        private String province;
        @ApiModelProperty(value = "预警信息")
        private MoJiWeather.MoreData moreData;
    }

    /**
     * 将天气信息转换为天气信息vo
     *
     * @param moJiWeather
     * @return
     */
    public static WeatherVo convert(MoJiWeather moJiWeather) {
        WeatherVo weatherVo = new WeatherVo();
        MoJiWeather.WeatherData weatherData = moJiWeather.getData();
        weatherVo.setCity(weatherData.getSourceName());
        List<MoJiWeather.Weather> list = weatherData.getList();
        weatherVo.setList(list);
        return weatherVo;
    }

    /**
     * 获得今天天气将天气信息转换为天气信息vo
     *
     * @param moJiWeather
     * @return
     */
    public static WeatherVo convertToDay(MoJiWeather moJiWeather) {
        WeatherVo weatherVo = new WeatherVo();
        MoJiWeather.WeatherData weatherData = moJiWeather.getData();

        MoJiWeather.Weather weather = weatherData.getList().get(0);
        weatherVo.setCity(weather.getCity());
        List<MoJiWeather.Weather> list = new ArrayList<>();
        list.add(weather);
        weatherVo.setList(list);
        return weatherVo;
    }
}