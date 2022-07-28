package com.king.open_api.controller;

import com.king.open_api.service.GetAddressFromIpService;
import com.king.open_api.service.WeatherServiceImpl;
import com.king.open_api.util.IPUtils;
import com.king.open_api.vo.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 12:14
 * @description:
 */
@RestController
@RequestMapping("/api/weather")
@Api(value = "天气接口", tags = "天气接口")
public class WeatherController {
    private final GetAddressFromIpService getAddressFromIpService;

    private final WeatherServiceImpl weatherService;

    @Autowired
    public WeatherController(GetAddressFromIpService getAddressFromIpService, WeatherServiceImpl weatherService) {
        this.getAddressFromIpService = getAddressFromIpService;
        this.weatherService = weatherService;
    }

    @ApiOperation(value = "获取天气信息", notes = "获取天气信息")
    @GetMapping("/getWeather.do")
    public ResultObj getWeather(HttpServletRequest request) {
        try {
            String ip = IPUtils.getIPAddress(request);
            String city = getAddressFromIpService.getAddressFromIp(ip).getCity();
            return ResultObj.success( weatherService.getWeather(city));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.error("获取天气信息失败");
        }

    }


    @ApiOperation(value = "获取今天天气信息", notes = "获取今天天气信息")
    @GetMapping("/getWeatherToDay.do")
    public ResultObj getWeatherToDay(HttpServletRequest request) {
        try {
            String ip = IPUtils.getIPAddress(request);
            String city = getAddressFromIpService.getAddressFromIp(ip).getCity();
            return ResultObj.success(weatherService.getTodayWeather(city));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.error("获取天气信息失败");
        }

    }

}