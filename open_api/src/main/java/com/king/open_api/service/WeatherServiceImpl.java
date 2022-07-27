package com.king.open_api.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.king.open_api.entity.MoJiWeather;
import com.king.open_api.vo.ResultObj;
import com.king.open_api.vo.WeatherVo;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 06:45
 * @description:
 */
@Service
public class WeatherServiceImpl {

    //讯飞语音识别内置的墨迹天气API
    private static final String WEATHER_URL_MOJI = "http://autodev.openspeech.cn/csp/api/v2.1/weather?openId=aiuicus&clientType=android&sign=android&city=%s&latitude=39.902895&longitude=116.427915&needMoreData=true&pageNo=1&pageSize=7";

    //中华万年历的天气API
    private static final String WEATHER_URL_WANNIU = "http://wthrcdn.etouch.cn/WeatherApi?city=%s";

    Logger logger = org.slf4j.LoggerFactory.getLogger(WeatherServiceImpl.class);

    //从墨迹天气API获取天气信息
    public MoJiWeather getWeatherFromMoJi(String city) {
        try {
            logger.info("城市：" + city);
            String url = String.format(WEATHER_URL_MOJI, city);
            String result = HttpUtil.get(url);
            //预警信息
            String s = StrUtil.toStringOrNull(JSON.parseObject(result).get("data.list[0].moreData"));
            System.out.println(s);
            MoJiWeather moJiWeather = JSON.parseObject(result, MoJiWeather.class);
            return moJiWeather;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据城市名称获取天气信息
     *
     * @param city
     * @return
     */
    public ResultObj getWeather(String city) {
        WeatherVo weatherVo = WeatherVo.convert(getWeatherFromMoJi(city));
        return ResultObj.success("success", weatherVo);
    }

    /**
     * 根据城市名称获取当天天气信息
     *
     * @param city
     * @return
     */
    public ResultObj getTodayWeather(String city) {
        WeatherVo weatherVo = WeatherVo.convertToDay(getWeatherFromMoJi(city));
        return ResultObj.success("success", weatherVo);
    }


    public static void main(String[] args) {
        WeatherServiceImpl weatherService = new WeatherServiceImpl();
        MoJiWeather moJiWeather = weatherService.getWeatherFromMoJi("北京");
        System.out.println(moJiWeather);
    }
}
