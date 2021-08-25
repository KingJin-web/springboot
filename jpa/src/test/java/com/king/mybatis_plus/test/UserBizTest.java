package com.king.mybatis_plus.test;

import com.king.biz.UserBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserBizTest {
    @Autowired
    UserBiz userBiz;

    public void printList(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void selectAll() {
        printList(userBiz.selectAll());
    }
}