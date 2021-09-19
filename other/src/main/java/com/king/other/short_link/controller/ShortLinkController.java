package com.king.other.short_link.controller;


import com.king.other.short_link.bean.ShortLink;
import com.king.other.short_link.service.ShortLinkServiceImpl;
import com.king.other.short_link.util.GetRequestIp;
import com.king.other.short_link.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;

import javax.naming.spi.DirStateFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            result.setData(shortLinkService.addShortLink(longLink, operation(request)));
            result.setStatus(true);
        }
        return result;
    }

    @RequestMapping(value = "/short/{shortLink}")
    public void sendRedirect(HttpServletResponse response,  @PathVariable String shortLink) {
        ShortLink shorts = shortLinkService.getLongLink(shortLink);
        if (shorts != null) {
            try {
                response.sendRedirect(shorts.getLongLink());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取请求的协议域名，端口号生成连接的前半部分
    public String operation(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme());
        System.out.println(url);
        url.append("://").append(request.getServerName());
        System.out.println(url);
        url.append(":").append(request.getServerPort());
        System.out.println(url);
//        url.append(request.getServletPath());
//        System.out.println(url);
        return url.toString();
    }
}
