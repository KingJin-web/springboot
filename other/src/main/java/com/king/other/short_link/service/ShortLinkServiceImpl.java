package com.king.other.short_link.service;

import com.king.other.short_link.bean.ShortLink;
import com.king.other.short_link.mapper.ShortLinkMapper;
import com.king.other.short_link.util.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

    public ShortLink addShortLink(String longLink, String url) {
        ShortLink shortLink = new ShortLink();
        shortLink.setLongLink(longLink);
        shortLink.setShorts(URLUtils.getShortURL(longLink));
        shortLink.setShortLink(url + shortLink.getShorts());
        return shortLink;
    }
}
