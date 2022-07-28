package com.king.open_api.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.king.open_api.entity.WeiBoHot;
import com.king.open_api.vo.NewsModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: King
 * @project: vibrator-spider
 * @date: 2022年07月28日 09:39
 * @description:
 */
@Service
public class GetHotNewsServiceImpl {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GetHotNewsServiceImpl.class);

    /**
     * 抓取百度热点排行榜
     *
     * @return
     */
    public List<NewsModel> grabBaiduHotNews() {
        String url = "https://top.baidu.com/board?tab=realtime&sa=fyb_realtime_31065";
        List<NewsModel> list = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            //标题
            Elements titles = doc.select(".c-single-text-ellipsis");
            //图片
            Elements imgs = doc.select(".category-wrap_iQLoo .index_1Ew5p").next("img");
            //内容
            Elements contents = doc.select(".hot-desc_1m_jR.large_nSuFU");
            //推荐图
            Elements urls = doc.select(".category-wrap_iQLoo a.img-wrapper_29V76");
            //热搜指数
            Elements levels = doc.select(".hot-index_1Bl1a");
            for (int i = 0; i < levels.size(); i++) {
                NewsModel o = new NewsModel();
                o.setTitle(titles.get(i).text().trim());
                o.setImg(imgs.get(i).attr("src"));
                o.setContent(contents.get(i).text().replaceAll("查看更多>", "").trim());
                o.setUrl(urls.get(i).attr("href"));
//                o.setLevel(levels.get(i).text().trim());
                list.add(o);
            }
            return list;

        } catch (IOException e) {
            logger.error("抓取百度热点排行榜异常：" + e.getMessage());
        }
        return null;
    }

    /**
     * 抓取微博热搜榜
     */
    public List<NewsModel> grabWeiBoHotNews() {
        String url = "https://weibo.com/ajax/statuses/hot_band";
        String s = HttpUtil.get(url);
        WeiBoHot weiBoHot = JSON.parseObject(s, WeiBoHot.class);
        List<NewsModel> list = new ArrayList<>();
        for (WeiBoHot.WeiBo weiBo : weiBoHot.getData().getBand_list()) {
            NewsModel o = new NewsModel();
            o.setTitle(weiBo.getNote());
            o.setImg(weiBo.getMblog());
            o.setContent(weiBo.getWord());
            o.setUrl(weiBo.getWord_scheme());
            list.add(o);
        }
        return list;
    }

    public String grabWeiBoHotNews2() {
        String url = "https://weibo.com/ajax/statuses/hot_band";

        String s = HttpUtil.get(url);
        System.out.println(s);
        WeiBoHot weiBoHot = JSON.parseObject(s, WeiBoHot.class);
        StringBuilder sb = new StringBuilder();
        for (WeiBoHot.WeiBo weiBo : weiBoHot.getData().getBand_list()) {
            sb.append(weiBo.getNote()).append("。");
        }
        return sb.toString();
    }


    public String grabBaiduHotNews2() {
        String url = "https://top.baidu.com/board?tab=realtime&sa=fyb_realtime_31065";
        StringBuffer sb = new StringBuffer();
        try {
            Document doc = Jsoup.connect(url).get();
            //标题
            Elements titles = doc.select(".c-single-text-ellipsis");

            for (Element title : titles) {
                sb.append(title.text().trim()).append("。");
            }
            return sb.toString();

        } catch (IOException e) {
            logger.error("抓取百度热点排行榜异常：" + e.getMessage());
        }
        return null;
    }

    public String grabHotNews2(Integer size) {
        try {
            List<NewsModel> list1 = grabBaiduHotNews();
            List<NewsModel> list2 = grabWeiBoHotNews();

            //60%的权重是百度，40%的权重是微博
            int l1 = size * 60 / 100;
            int l2 = size - l1;
            if (list1.size() < l1) {
                l1 = list1.size();
                l2 = size - l1;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < l1; i++) {
                sb.append(list1.get(i).getTitle()).append("。");
            }
            if (list2.size() < l2) {
                l2 = list2.size();
            }
            for (int i = 0; i < l2; i++) {
                sb.append(list2.get(i).getTitle()).append("。");
            }
            return sb.toString();
        } catch (Exception e) {
            logger.error("抓取热点排行榜异常：", e);
        }
        return null;

    }
}