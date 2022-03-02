package com.king.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 11:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin") // 添加用户admin
                .password("{noop}aaaa")  // 不设置密码加密
                .roles("ADMIN", "USER")// 添加角色为admin，user
                .and()
                .withUser("user") // 添加用户user
                .password("{noop}bbbb")
                .roles("USER")
                .and()
                .withUser("temp") // temp
                .password("{noop}cccc")
                .roles(); // 没有角色
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasRole("USER") //添加/user/** 下的所有请求只能由user角色才能访问
                .antMatchers("/admin/**").hasRole("ADMIN") //添加/admin/** 下的所有请求只能由admin角色才能访问
                .anyRequest().authenticated() // 没有定义的请求，所有的角色都可以访问（tmp也可以）。
                .and()
                .formLogin().and()
                .httpBasic();
    }


}
