package com.king.security.controller;

import com.king.security.service.LoginUserServiceImpl;
import com.king.security.vo.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 12613
 * @project: springboot
 * @pcakage: com.king.security.controller.LoginUserController
 * @date: 2022年05月05日 21:38
 * @description: ${}
 */
@RestController
@RequestMapping("/api/loginUser")
@Api(value = "获取全部登录用户", tags = "获取全部登录用户")
public class LoginUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginUserServiceImpl loginUserService;

    @ApiOperation("获取全部登录用户")
    @GetMapping("/getAllLoginUser.do")
    public ResultObj getAllLoginUser() {
        return ResultObj.ok(loginUserService.getLoginUser());
    }
}