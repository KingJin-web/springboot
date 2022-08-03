package com.king.open_api.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.king.open_api.entity.TencentNews;
import com.king.open_api.vo.NewsModel;
import com.king.open_api.vo.ResultObj;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: King
 * @project: vibrator-spider
 * @date: 2022年07月24日 03:11
 * @description:
 */
@Service
public class NewsApiService {
    //腾讯新闻接口
    public static final String TENCENT_NEWS_API = "https://i.news.qq.com/trpc.qqnews_web.kv_srv.kv_srv_http_proxy/list?sub_srv_id=24hours&srv_id=pc&offset=0&limit=30&strategy=1&ext={%22pool%22:[%22top%22],%22is_filter%22:7,%22check_type%22:true}";
    //新浪新闻接口
    public static final String SINA_NEWS_API = "https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00ZQZQZ0qZ0qZCb8c8f8f8f8f8f8f8f&count=30";
    //网易新闻接口
    public static final String NETEASE_NEWS_API = "https://api.caijing.com.cn/api/v2/news/list?page=1&page_size=20&cid=&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=&uid=&mac=&mid=&idfa=&openudid=&title=&abtest=&udid=&wifi=1&v=2&sudaref=&lng=&lat=&city=&province=&country=&isp=&network_type=&language=&os=&os_version=&device_type=&resolution=&aid=12050&token=&_signature=0A5F8F8F7F7B1C1";
    //澎湃新闻接口
    public static final String PENPAI_NEWS_API = "https://api.caijing.com.cn/api/v2/news/list?page=1&page_size=20&cid=&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=&uid=&mac=&mid=&idfa=&openudid=&title=&abtest=&udid=&wifi=1&v=2&sudaref=&lng=&lat=&city=&province=&country=&isp=&network_type=&language=&os=&os_version=&device_type=&resolution=&aid=12050&token=&_signature=0A5F8F8F7F7B1C1";

    Logger logger = org.slf4j.LoggerFactory.getLogger(NewsApiService.class);

    public ResultObj getNews() {
        String s = getTencentNews();
        TencentNews tencentNews = JSON.parseObject(s, TencentNews.class);
        NewsModel newsModel = tencentNews.toNewsModel();
//        logger.info("获取到的新闻内容：{}", newsModel);
        return ResultObj.success(newsModel);
    }

    //获取腾讯新闻
    public String getTencentNews() {
        return HttpUtil.get(TENCENT_NEWS_API);
    }


    public ResultObj getNewsBySize(Integer size) {
        String s = getTencentNews();
        TencentNews tencentNews = JSON.parseObject(s, TencentNews.class);
        List<NewsModel> newsModels = tencentNews.toNewsModelList(size);
        logger.info("获取到的新闻内容：{}", newsModels);
        return ResultObj.success(newsModels);
    }

    //获取沙雕新闻
}