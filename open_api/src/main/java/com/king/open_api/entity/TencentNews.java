package com.king.open_api.entity;

import com.king.open_api.vo.NewsModel;
import lombok.Data;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author: King
 * @project: vibrator-spider
 * @date: 2022年07月24日 03:22
 * @description:
 */
@Data
@Controller
public class TencentNews {
    private Integer ret;
    private String msg;
    private Data data;

    public List<NewsModel> toNewsModelList(int size) {
        List<News> list = data.list;
        //随机取出size个新闻
        Random random = new Random();
        if (list.size() < size) {
            size = list.size();
        }
        Set<Integer> set = random.ints(0, list.size()).distinct().limit(size).boxed().collect(java.util.stream.Collectors.toSet());

        return list.stream().filter(x -> set.contains(list.indexOf(x))).map(x -> {
            NewsModel newsModel = new NewsModel();
            newsModel.setUrl(x.url);
            newsModel.setSource(x.media_name);
            newsModel.setContent(x.title);
            return newsModel;
        }).collect(java.util.stream.Collectors.toList());
    }

    @lombok.Data
    public static class Data {
        List<News> list;
    }

    @lombok.Data
    public static class News {
        private String cms_id;
        // 新闻标题
        private String title;
        // 新闻链接
        private String url;
        // 新闻
        private String source;
        // 新闻发布时间
        private String create_time;
        private String update_time;
        // 新闻图片
        private String img;
        private String category_cn;
        private String sub_category_cn;
        //来源
        private String media_name;
    }

    //转为NewsModel对象
    public NewsModel toNewsModel() {
        NewsModel newsModel = new NewsModel();
        List<News> list = data.list;
        Random random = new Random();
        int index = random.nextInt(list.size());
        newsModel.setUrl(list.get(index).url);
        newsModel.setSource(list.get(index).source);
        newsModel.setContent(list.get(index).title);
        return newsModel;
    }
}