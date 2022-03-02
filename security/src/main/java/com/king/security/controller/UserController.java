package com.king.security.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 13:03
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello User!";
    }
}
