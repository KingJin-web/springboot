package com.king.security.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 13:00
 */
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@Api(value = "管理员操作接口",tags = "管理员操作接口")
public class AdminController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Admin!";
    }
}
