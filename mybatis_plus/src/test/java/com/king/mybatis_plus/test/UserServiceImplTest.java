package com.king.mybatis_plus.test;

import com.king.mybatis_plus.bean.User;
import com.king.mybatis_plus.service.UserServiceImpl;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;


    @Test
    public void queryAll() {
        userService.queryAll().forEach(System.out::println);
    }

    @Test
    public void queryById() {
        User user = userService.queryById(User.builder().id(2).build());
        System.out.println(user);
    }

    @Test
    public void deleteById() {

    }

    @Test
    public void change() {
        User user1 = User.builder().name("蔡徐坤").build();
        userService.changeBy(user1, "sex", "男");

        user1.setName("蔡徐坤2");
        userService.changeUserById(user1);
    }
}