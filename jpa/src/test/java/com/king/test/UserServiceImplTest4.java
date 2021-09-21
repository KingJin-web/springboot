package com.king.test;

import com.king.bean.User;
import com.king.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: springboot
 * @description: jpa 排序
 * @author: King
 * @create: 2021-09-20 21:55
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest4 {

    @Autowired
    UserDao userDao;

    @Test
    public void test1() {
        //建立分页对象: 参数 1 页数 参数 2 每页数据量
        Pageable pageable = PageRequest.of(3, 10);
        Page<User> page = userDao.findByOrderByIdDesc(pageable);
        page.forEach(System.out::println);
        System.out.println(pageable);
    }

    @Test
    public void test2() {
        Pageable pageable = PageRequest.of(3, 10);
        Page<User> page = userDao.findInOrders(pageable);
        page.forEach(System.out::println);
    }

    @Test
    public void test3() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(3, 10, sort);
        Page<User> page = userDao.findAll(pageable);
        page.forEach(System.out::println);
    }

}
