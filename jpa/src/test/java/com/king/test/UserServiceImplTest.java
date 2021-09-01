package com.king.test;

import com.king.bean.User;
import com.king.dao.UserDao;
import org.junit.After;
import org.junit.Before;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserDao userDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("最后查询全部");
        userDao.findAll().forEach(System.out::println);
    }

    @Test
    public void query() {
//        userDao.findUser("test1").println();
//        userDao.findByName("test1").forEach(System.out::println);
//        userDao.findByNameLike("%test1%").forEach(System.out::println);

        userDao.findUserById(2).println();
    }

    @Test
    public void add() {
        User user = new User(1, "test", "男", "aaa", "a@qq.com");
        //userDao.saveAndFlush(user);

        List<User> list = new ArrayList<>();
        for (int i = 20; i < 40; i++) {
            User user1 = new User(i, "test" + i, i % 2 == 0 ? "男" : "女", "aaaa", "a" + i + "@qq.com");
            list.add(user1);
        }

        //userDao.saveAll(list);

    }

    @Test
    public void update() {
//        User(id=37, name=test37, sex=女, pwd=aaaa, email=a37@qq.com)
//        User(id=38, name=test38, sex=男, pwd=aaaa, email=a38@qq.com)
//        User(id=39, name=test39, sex=女, pwd=aaaa, email=a39@qq.com)
        User user = userDao.findUserById(39);
        System.out.println(user);
        user.setSex("??");
        userDao.save(user);
    }

    @Test
    public void delete() {
//        User user = new User();
//        user.setId(20);
//        userDao.delete(user);
//        List<Integer> list = new ArrayList<>();
//        list.add(21);
//        list.add(22);
//        userDao.deleteAllById(list);

        User user1 = new User();
        user1.setId(40);
        userDao.delete(user1);
    }

}