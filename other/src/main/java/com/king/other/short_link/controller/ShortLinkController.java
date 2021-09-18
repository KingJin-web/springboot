package com.king.other.short_link.controller;


import com.king.other.short_link.service.ShortLinkServiceImpl;
import com.king.other.short_link.util.GetRequestIp;
import com.king.other.short_link.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.spi.DirStateFactory;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-18 14:20
 */
@RestController

public class ShortLinkController {
    @Autowired
    ShortLinkServiceImpl shortLinkService;

    @RequestMapping(value = "/share.do")
    public Result shareLink(HttpServletRequest request, String longLink) {
        Result result = new Result();
        if (longLink != null) {
            result.setData(shortLinkService.addShortLink(longLink,operation(request)));
            result.setStatus(true);
        }
        return result;
    }

    public String operation(HttpServletRequest request) {
        String url = "";

        url = request.getScheme() + "://" + request.getServerName()

                + ":" + request.getServerPort()

                + request.getServletPath();

        if (request.getQueryString() != null) {
            url += "?" + request.getQueryString();

        }

        System.out.println(url);

        return url;

    }
}
