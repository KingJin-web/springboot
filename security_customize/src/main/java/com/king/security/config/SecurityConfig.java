package com.king.security.config;

import com.king.security.config.login.DefaultAuthenticationFailureHandler;
import com.king.security.config.login.DefaultAuthenticationSuccessHandler;
import com.king.security.config.login.LoginFilter;
import com.king.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-13 08:28
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法权限控制
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    @Autowired
    private DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler;
    @Autowired
    LoginFilter loginFilter;

    /**
     * 加密方式
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @Description: 主配置方法2
     * @author tanleijin
     * @date 2019/9/10 15:03
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //4.配置自己实现的登录认证的service,并设置密码的加密方式（）
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //静态资源配置
    @Override
    public void configure(WebSecurity web) throws Exception {
        //swagger2所需要用到的静态资源，允许访问
        web.ignoring().antMatchers("/swagger/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/v2/api-docs-ext/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/doc.html")

                .antMatchers("/lib/**")
                .antMatchers("/layer/**")
                .antMatchers("/layui/**")
                .antMatchers("/layui/css/**")
                .antMatchers("/login.html")
                .antMatchers("/register.html")
                .antMatchers("/agreement.html")
                .antMatchers("/index.html");

    }

    /**
     * @Description: 主配置方法1
     * @author tanleijin
     * @date 2019/9/10 15:03
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨域保护
        http.csrf().disable();
        //验证码过滤器
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        //放行验证码
        http.authorizeRequests().antMatchers("/login_code.png",
                "/api/user/register.do",
                "/api/code/sendSms.do",
                "/register").permitAll();
        // 指定指定要的登录页面
        http.formLogin().loginPage("/login").loginProcessingUrl("/api/user/login.do")
                .successHandler(defaultAuthenticationSuccessHandler)
                .failureHandler(defaultAuthenticationFailureHandler).permitAll();
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.logout().logoutUrl("logout.do");

    }

}
