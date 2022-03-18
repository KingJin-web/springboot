package com.king.security.controller;

import com.king.security.service.TxSmsServiceImpl;
import com.king.security.util.MyException;
import com.king.security.util.StringUtils;
import com.king.security.vo.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-18 13:27
 */
@RestController
@PermitAll
@RequestMapping("/api/code")
@Api(value = "验证码接口", tags = "验证码接口")
public class CodeController {
    @Autowired
    private TxSmsServiceImpl txSmsService;

    @ApiOperation(value = "发送注册验证码", httpMethod = "POST")
    @ApiImplicitParam(name = "phone", value = "电话号码", dataType = "string", paramType = "query", example = "13788888888", required = true)
    @PostMapping("/sendSms.do")
    public ResultObj sendCodeRegister(String phone, HttpServletRequest request) {
        try {
            StringUtils.isPhone(phone);
            HttpSession session = request.getSession();
            Date lastDate = (Date) session.getAttribute("code_date");
            if (lastDate != null && (new Date().getTime() - lastDate.getTime()) < (1000 * 120)) {
                return ResultObj.error("别点了，等" + (120 - ((new Date().getTime() - lastDate.getTime()) / 1000)) + "秒后再点");
            } else {
                String code = StringUtils.getInt(6);
                session.setAttribute("code", code);//存验证码到session
                session.setAttribute("code_date", new Date());//存当前时间到session
                return ResultObj.ok(txSmsService.sendSmsCode(phone, code));
            }
        } catch (MyException e) {
            return ResultObj.error(e);
        }
    }
}
