package com.king.security.controller;


import com.king.security.vo.ResultObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public String hello() {
        return "Hello User!";
    }

    @PostMapping(value = "/register.do")
    @ApiOperation(value = "注册用户", tags = "用户操作接口")
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

    @PostMapping(value = "/login.do")
    @ApiOperation(value = "用户登录", tags = "用户操作接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "用户名,或邮箱", dataType = "string", paramType = "query", example = "king", required = true),
            @ApiImplicitParam(name = "password", value = "登录密码", dataType = "string", paramType = "query", example = "aaaa", required = true),
            @ApiImplicitParam(name = "validate_code", value = "登录验证码", dataType = "string", paramType = "query", example = "3679", required = true)

    })
    public ResultObj login(HttpServletRequest req, String s, String password, String validate_code) {
        ResultObj resultObj = new ResultObj();


        return resultObj;
    }
}