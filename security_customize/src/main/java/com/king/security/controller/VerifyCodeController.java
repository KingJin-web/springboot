package com.king.security.controller;

import com.king.security.util.VerifyCodeGen;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController()
@Api(value = "图形验证码接口", tags = "图形验证码接口")
public class VerifyCodeController {

    @ApiOperation(value = "获取登录图形验证码", tags = "图形验证码接口")
    @GetMapping(value = "/login_code.png")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("login_code", VerifyCodeGen.outputImage(resp));
    }
}
