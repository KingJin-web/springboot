package com.king.security.config;

import com.king.security.config.login.DefaultAuthenticationFailureHandler;
import com.king.security.config.login.DefaultAuthenticationSuccessHandler;
import com.king.security.config.login.LoginFilter;
import com.king.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
    private LoginFilter loginFilter;

    /**
     * 加密方式
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     * @throws Exception
     * @author King
     * @date 2020/3/13
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //4.配置自己实现的登录认证的service,并设置密码的加密方式（）
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //静态资源配置
    @Override
    public void configure(WebSecurity web) throws Exception {
        //swagger2所需要用到的静态资源，允许访问
        web.ignoring().antMatchers("/swagger/**").antMatchers("/swagger-ui.html").antMatchers("/webjars/**").antMatchers("/v2/**").antMatchers("/v2/api-docs-ext/**").antMatchers("/swagger-resources/**").antMatchers("/doc.html").antMatchers("/lib/**").antMatchers("/layer/**").antMatchers("/layui/**").antMatchers("/layui/css/**").antMatchers("/login.html").antMatchers("/register.html").antMatchers("/agreement.html").antMatchers("/index.html");

    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨域保护
        http.csrf().disable();
        //验证码过滤器
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        //放行验证码等接口
        http.authorizeRequests().antMatchers("/login_code.png", "/api/user/register.do", "/api/code/sendSms.do", "/register").permitAll();

        http.sessionManagement()
                // 设置 Session 会话失效时重定向路径，默认为 loginPage()
                // .invalidSessionUrl("/login")
                // 配置使用自定义的 Session 会话失效处理策略
//                .invalidSessionStrategy(invalidSessionStrategy)
                // 设置单用户的 Session 最大并发会话数量，-1 表示不受限制
                .maximumSessions(1)
                // 设置为 true，表示某用户达到最大会话并发数后，新会话请求会被拒绝登录
                .maxSessionsPreventsLogin(true)
                // 设置所要使用的 sessionRegistry，默认为 SessionRegistryImpl 实现类
                .sessionRegistry(sessionRegistry());
        // 指定指定要的登录页面
        http.formLogin().loginPage("/login").loginProcessingUrl("/api/user/login.do").successHandler(defaultAuthenticationSuccessHandler).failureHandler(defaultAuthenticationFailureHandler).permitAll();
        http.authorizeRequests().anyRequest().fullyAuthenticated();

        //开启记住我功能
        http.rememberMe().rememberMeParameter("rememberMe");

        //退出登录
        http.logout().logoutUrl("logout.do")
                .logoutSuccessUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do", "GET"))
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // System.out.println("onLogoutSuccess");
                        response.sendRedirect("logout.html");
                    }
                })
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        System.out.println("logout");
                    }
                })
                .invalidateHttpSession(true)
                .deleteCookies("token_token");

    }

}
