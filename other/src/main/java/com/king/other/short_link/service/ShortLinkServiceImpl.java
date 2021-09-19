package com.king.other.short_link.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.king.other.short_link.bean.ShortLink;
import com.king.other.short_link.mapper.ShortLinkMapper;
import com.king.other.short_link.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-18 14:31
 */
@Service
public class ShortLinkServiceImpl {

    private ShortLinkMapper shortLinkMapper;

    @Autowired
    public void setShortLinkMapper(ShortLinkMapper shortLinkMapper) {
        this.shortLinkMapper = shortLinkMapper;
    }

    //通过长链生成对应短链并存在数据库中
    public ShortLink addShortLink(String longLink, String url) {
        url += "/short";
        ShortLink shortLink = new ShortLink();
        shortLink.setLongLink(longLink);
        shortLink.setShorts(URLUtil.getShortURL());
        shortLink.setShortLink(url + "/" + shortLink.getShorts());
        shortLinkMapper.insert(shortLink);
        return shortLink;
    }

    //通过短链查询到对应长链
    public  ShortLink getLongLink(String shorts) {
        QueryWrapper<ShortLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shorts", shorts);
        return shortLinkMapper.selectOne(queryWrapper);
    }
}
