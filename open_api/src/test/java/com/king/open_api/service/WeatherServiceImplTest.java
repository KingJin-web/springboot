package com.king.open_api.service;

import com.king.open_api.entity.MoJiWeather;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class WeatherServiceImplTest {

    @Autowired
    WeatherServiceImpl weatherService;

    @Test
    public void getWeatherFromMoJi() {
        MoJiWeather moJiWeather = weatherService.getWeatherFromMoJi("北京");
        System.out.println(moJiWeather);
    }

    @Test
    public void getWeather() {
    }

    @Test
    public void getTodayWeather() {
    }

    @Test
    public void main() {
    }
}