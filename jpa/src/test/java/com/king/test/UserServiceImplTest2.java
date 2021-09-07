package com.king.test;

import com.king.bean.User;
import com.king.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-07 12:42
 */

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Junit5单元测试")
public class UserServiceImplTest2 {
    Example<User> userExample = null;
    @Autowired
    private UserDao userDao;

    @Test
    @DisplayName("模糊条件查询")
    public void test1() {
        //userDao.findAll().forEach(System.out::println);
        User user = new User();
        user.setSex("男");
        user.setEmail("@qq.com");
        user.setName("test2");
        user.setPwd("aa");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.startsWith()) //模糊查询匹配开头，即{name}%
                .withMatcher("pwd", match -> match.contains()) //全部模糊查询，即%{address}%
                .withMatcher("email", match -> match.endsWith()) //模糊查询匹配末尾，即%{email}
                .withIgnorePaths("sex");//忽略字段，即不管sex是什么值都不加入查询条件

        userExample = Example.of(user, matcher);
        userDao.findAll(userExample).forEach(System.out::println);
    }

    @Test
    @DisplayName("测试条件查询")
    public void test2() {
        //userDao.findAll().forEach(System.out::println);
        User user = new User();
        user.setSex("男");
        user.setEmail("@qq.com");
        user.setName("test2");
        user.setPwd("aa");
        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        userExample = Example.of(user, matcher);
        userDao.findAll(userExample).forEach(System.out::println);
    }
}

