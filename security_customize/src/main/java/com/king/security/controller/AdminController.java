package com.king.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 13:00
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello Admin!";
    }
}
