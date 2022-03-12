package com.king.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: springboot
 * @description: 验证码拦截器
 * @author: King
 * @create: 2022-03-12 06:16
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            //请求中传来的验证码
            String code = request.getParameter("login_code");
            //session 存储的验证码
            String kaptchaCode = (String) request.getSession().getAttribute("login_code");
            logger.info("用户输入验证码：{}========session中存的验证码：{}", code, kaptchaCode);
            if (StringUtils.isEmpty(code)) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(401);
                PrintWriter printWriter = response.getWriter();
              //  HTTPResponse httpResponse = HTTPResponse.error("验证码不能为空！", "");
                printWriter.write(new ObjectMapper().writeValueAsString("验证码不能为空！"));
                printWriter.flush();
                printWriter.close();
                return;
            } else if (!kaptchaCode.toLowerCase().equals(code.toLowerCase())) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(401);
                PrintWriter printWriter = response.getWriter();
//                HTTPResponse httpResponse = HTTPResponse.error("验证码错误！", "");
                printWriter.write(new ObjectMapper().writeValueAsString("验证码错误！"));
                printWriter.flush();
                printWriter.close();
                return;
            }
        }
        chain.doFilter(request, response);
    }
}