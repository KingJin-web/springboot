package com.king.mybatis.test;

import com.king.mybatis.bean.User;
import com.king.mybatis.service.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;
    @Test
    public void queryAll() {
        userService.queryAll().forEach(System.out::println);
    }

    @Test
    public void add() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 15; ++i) {
            User user = User.builder().id(i + 1).name("test" + i).sex(i % 2 == 0 ? "男" : "女").pwd("aaaa").email("123" + i + "@qq.com").build();

            users.add(user);
        }
        users.forEach(System.out::println);
        userService.add(users);


    }

    @Test
    public void query() {
        userService.queryAll().forEach(System.out::println);

        userService.queryByName1("test1").forEach(System.out::println);
        userService.queryByName2("test1").forEach(System.out::println);
        User user = userService.queryById(User.builder().id(2).build());
        System.out.println(user);
        userService.queryByNameMap("test1").forEach(System.out::println);
        System.out.println(userService.count());
    }

    @Test
    public void delete() {
        User user = User.builder().id(2).build();
        userService.deleteById(user);

        userService.deleteBy("name", "test15");

        userService.deleteByIds();

    }

    @Test
    public void change() {
        User user1 = User.builder().name("蔡徐坤").build();
        userService.changeBy(user1, "sex", "男");

        user1.setName("蔡徐坤2");
        userService.changeUserById(user1);
    }

}