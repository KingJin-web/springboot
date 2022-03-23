package com.king.security.config.login;

import com.alibaba.fastjson.JSON;
import com.king.security.vo.ResultObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-13 08:43
 */
@Component
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException,
            ServletException {
        logger.info("login in failure : " + e.getMessage());
        ResultObj resultObj = new ResultObj();
        if (e instanceof LockedException) {
            resultObj = ResultObj.error("账户被锁定，登录失败！");
        } else if (e instanceof BadCredentialsException) {
            resultObj = ResultObj.error("账户名或密码输入错误，登录失败！");
        } else if (e instanceof DisabledException) {
            resultObj = ResultObj.error("账户被禁用，登录失败！");
        } else if (e instanceof AccountExpiredException) {
            resultObj = ResultObj.error("账户已过期，登录失败！");
        } else if (e instanceof CredentialsExpiredException) {
            resultObj = ResultObj.error("账户已过期，登录失败！");
        } else {
            resultObj = ResultObj.error(e.getMessage());
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(resultObj));
    }




}
