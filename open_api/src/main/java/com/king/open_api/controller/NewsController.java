package com.king.open_api.controller;

import com.king.open_api.service.NewsApiService;
import com.king.open_api.vo.ResultObj;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: King
 * @project: vibrator-spider
 * @date: 2022年07月25日 13:42
 * @description:
 */
@RestController
@RequestMapping("/api/news")
@Api(value = "新闻", tags = "新闻")
public class NewsController {
    private NewsApiService newsApiService;

    @Autowired
    public void setNewsApiService(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @GetMapping("/getNews.do")
    public ResultObj getNews() {
        return newsApiService.getNews();
    }

    @GetMapping("/getNewsBySize.do")
    public ResultObj getNewsBySize(Integer size) {
        return newsApiService.getNewsBySize(size);
    }


}