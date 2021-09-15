package com.king.mybatis.test;

import com.king.mybatis.bean.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: springboot
 * @description: 扩展测试类
 * @author: King
 * @create: 2021-09-12 00:50
 */
@SpringBootTest
public class UserExtendTest {
    @Test
    @DisplayName("自动填充插入测试")
    public void test1() {
        User user = User.builder().id(1).name("胡歌").sex("男").build();
        user.insert();
        System.out.println("插入后");
        user.selectById(1).println();
    }

    @Test
    @DisplayName("自动填充修改测试")
    public void test2() {
        User user = User.builder().build().selectById(1);
        User user1 = user;
        user.println();
        user.setName("李逍遥1");
        user.setPwd("aaaaa");
        user.updateById();
        User user2 = user.selectById(1);
        System.out.println("==========修改前==========");
        user1.println();
        System.out.println("==========修改后==========");
        user2.println();
    }

    @Test
    @DisplayName("逻辑删除测试")
    public void test3() {
        User user = User.builder().build().selectById(1);
        user.deleteById();
        User user2 = User.builder().build().selectById(1);
        System.out.println("======删除后======");
        Assertions.assertNull(user2);
    }
}
