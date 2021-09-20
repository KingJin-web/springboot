package com.king.test;

import com.king.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-20 17:13
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest3 {
    @Autowired
    UserDao userDao;

    @Test
    public void test1(){
        userDao.queryByName("test",10).forEach(o -> System.out.println(o));
    }

}
