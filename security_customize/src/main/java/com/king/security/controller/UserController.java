package com.king.security.controller;


import com.king.security.vo.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 13:03
 */
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Api(value = "用户操作接口",tags = "用户操作接口")
public class UserController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello User!";
    }

    @PostMapping(value = "/register.do")
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query", example = "lihailin9073", required = true),
            @ApiImplicitParam(name = "pwd1", value = "登录密码", dataType = "string", paramType = "query", example = "123456", required = true),
            @ApiImplicitParam(name = "pwd2", value = "确认密码", dataType = "string", paramType = "query", example = "123456", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "string", paramType = "query", example = "jinpeng.qmail@qq.com", required = true),
            @ApiImplicitParam(name = "validate_code", value = "注册验证码", dataType = "string", paramType = "query", example = "3679", required = true)

    })
    public ResultObj register(String name, String pwd1, String pwd2, String email, String validate_code) {
        ResultObj resultObj = new ResultObj();


        return resultObj;

    }


    /**
     * 获得用户登录信息
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表", httpMethod = "GET")
    @GetMapping("/info")
    public Object info() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}