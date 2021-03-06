package com.king.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 00:34
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    /**
     * 获得用户登录信息
     * @return
     */
    @RequestMapping("/info")
    public Object info(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
