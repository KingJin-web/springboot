package com.king.security.config.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springboot
 * @description: 验证码拦截器
 * @author: King
 * @create: 2022-03-12 06:16
 */
@Component
public class LoginFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DefaultAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        //如果是 登录请求 则执行
        if ((request.getMethod().equalsIgnoreCase("post") &&
                request.getRequestURI().endsWith("/login.do"))) {
            try {
                validate(request, response);
            } catch (ValidateCodeException e) {
                //调用错误处理器，最终调用自己的
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;//结束方法，不再调用过滤器链
            }
        }
        chain.doFilter(request, response);
    }

    public void validate(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException {
        //请求中传来的验证码
        String code = request.getParameter("login_code");
        //session 存储的验证码
        String session_Code = (String) request.getSession().getAttribute("login_code");
        logger.info("用户输入验证码：{}========session中存的验证码：{}", code, session_Code);
        if (StringUtils.isEmpty(code)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (StringUtils.isEmpty(session_Code)) {
            throw new ValidateCodeException("验证码已经失效！");
        }
        if (!session_Code.equalsIgnoreCase(code)) {
            throw new ValidateCodeException("验证码输入错误！");
        }

    }


    /**
     * 验证码错误异常，继承spring security的认证异常
     */
    public static class ValidateCodeException extends AuthenticationException {
        /**
         * @Fields serialVersionUID : TODO
         */
        private static final long serialVersionUID = 1L;

        public ValidateCodeException(String msg) {
            super(msg);
        }
    }
}