package com.king.other.short_link.service;

import com.king.other.short_link.util.URLUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ShortLinkServiceImplTest {


    @Test
    public void setShortLinkMapper() {
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            String s = URLUtils.getShortURL("https://www.baidu.com/");
            map.put(s,map.getOrDefault(s,0) + 1);
        }
        System.out.println(map.size());
    }

    @Autowired
    ShortLinkServiceImpl shortLinkService;
    @Test
    public void add() {
        shortLinkService.addShortLink("https://blog.csdn.net/weixin_43829443/article/details/108092785","lost");
    }
}