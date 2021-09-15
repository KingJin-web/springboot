package com.king.mybatis.controller;

import com.king.mybatis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-09 16:32
 */
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;
}
