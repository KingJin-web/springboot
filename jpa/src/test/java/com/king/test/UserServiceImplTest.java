package com.king.test;

import com.king.bean.User;
import com.king.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserDao userDao;

    //查询全部
    @Test
    public void queryAll() {
        userDao.findAll().forEach(System.out::println);
    }


    @Before
    public void setUp() throws Exception {

    }

    //    @After
    public void tearDown() throws Exception {
        System.out.println("最后查询全部");
        userDao.findAll().forEach(System.out::println);
    }

//    @Autowired
//    Example<User> userExample;

    @Test
    public void query() {
//        userDao.findUser("test1").println();
//        userDao.findByName("test1").forEach(System.out::println);
//        userDao.findByNameLike("%test1%").forEach(System.out::println);
//        userDao.findUserById(2).println();

        //查询全部
        userDao.findAll().forEach(System.out::println);
        //通过id查询
        userDao.findById(8).get().println();
        //条件查询所有性别为男的用户
        User user = new User();
        user.setSex("男");
        Example<User> example = Example.of(user);  // 此处未构建自定义匹配规则,使用的是默认匹配规则
        userDao.findAll(example).forEach(System.out::println);

        user.setName("test2");
        //查询一个 性别为男 名字为 test2的用户
        userDao.findOne(example).get().println();
    }


    @Test
    public void add() {
        User user = new User(null, "test", "男", "aaa", "a@qq.com");
        userDao.saveAndFlush(user);

        List<User> list = new ArrayList<>();
        for (int i = 20; i < 40; i++) {
            User user1 = new User(null, "test" + i, i % 2 == 0 ? "男" : "女", "aaaa", "a" + i + "@qq.com");
            list.add(user1);
        }
        userDao.saveAll(list);
    }

    @Test
    public void update() {
        //获得id为8的用户对象
        User user = userDao.findById(8).get();
        user.println();
        user.setEmail("update@qq.com");
        //提交修改
        User user1 = userDao.save(user);
        //打印修改后结果
        user1.println();
    }

    @Test
    public void delete() {
        User user = new User();
        user.setId(20);
        userDao.delete(user);
//        List<Integer> list = new ArrayList<>();
//        list.add(21);
//        list.add(22);
//        userDao.deleteAllById(list);

//        User user1 = new User();
//        user1.setId(40);
//        userDao.delete(user1);

        List<User> list = new ArrayList<>();
        list.add(new User(40, "test21", "", "", ""));
        list.add(new User(null, "test20", "男", "", ""));
        userDao.deleteAll(list);
    }

}