package com.king.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 00:34
 */
@Controller
public class HelloController {
    @RequestMapping("login")
    public String openLogin() {
        return "login.html";
    }

    @RequestMapping("/register")
    public String openRegister() {
        return "register.html";
    }

}
