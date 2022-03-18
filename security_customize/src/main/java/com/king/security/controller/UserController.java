package com.king.security.controller;


import com.king.security.entity.Role;
import com.king.security.service.UserServiceImpl;
import com.king.security.util.MyException;
import com.king.security.util.StringUtils;
import com.king.security.vo.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 13:03
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "用户操作接口", tags = "用户操作接口")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/hello")
    public String hello() {
        return "Hello User!";
    }

    /**
     * 获得用户登录信息
     *
     * @return
     */
    @ApiOperation(value = "获得用户登录信息", httpMethod = "GET")
    @GetMapping("/info")
    public Object info() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping(value = "/register.do")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query", example = "lihailin9073", required = true),
            @ApiImplicitParam(name = "pwd1", value = "登录密码", dataType = "string", paramType = "query", example = "123456", required = true),
            @ApiImplicitParam(name = "pwd2", value = "确认密码", dataType = "string", paramType = "query", example = "123456", required = true),
            @ApiImplicitParam(name = "phone", value = "phone", dataType = "string", paramType = "query", example = "13788888888", required = true),
            @ApiImplicitParam(name = "registerCode", value = "注册验证码", dataType = "string", paramType = "query", example = "3679", required = true)

    })
    public ResultObj register(String name, String pwd1, String pwd2, String phone, String registerCode, HttpServletRequest request) {
        try {

            // 密码不能为空且一样
            StringUtils.pwdCheckNull(pwd1, pwd2);
            //用户名规范
            StringUtils.nameIsOk(name);
            if (userService.isUserName(name)) {
                return ResultObj.error("用户名已经被使用！");
            }
            //电话号码规范
            StringUtils.isPhone(phone);

            HttpSession session = request.getSession();
            //获取存在session的验证码
            String sessionCode = (String) session.getAttribute("code");
            if (sessionCode != null && sessionCode.equals(registerCode)) {
                return ResultObj.ok(userService.registerByEncode(name, pwd1, phone, Role.USER));
            } else {
                return ResultObj.error("验证码错误！");
            }

        } catch (MyException e) {
            return ResultObj.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.error(e.getMessage());
        }
    }


}