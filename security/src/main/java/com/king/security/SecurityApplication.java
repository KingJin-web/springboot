package com.king.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SecurityApplication {
//https://www.cnblogs.com/dl610455894/p/14072960.html
    //学习Spring Security需要记住以下两个类：
//WebSecurityConfigurerAdapter：自定义Security策略
//AuthenticationManagerBuilder：自定义认证策略
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);

    }

}
