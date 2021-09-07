package com.king.mybatis_plus.test;

import com.king.bean.User;
import com.king.biz.UserBiz;

import com.king.util.Helper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserBizTest {

    @Autowired
    UserBiz userBiz;

    @Test
    public void selectByAccountAndPwd() {
        System.out.println(userBiz.selectById(2));
        Helper.print(userBiz.queryAll());
    }

    @Test
    public void queryAll() {
        userBiz.queryAll().forEach(System.out::println);
    }

    @Test
    public void queryByName() {
        long t1 = new Date().getTime();
        Map<String, Object> maps = userBiz.queryByName1("张三");
        long t2 = new Date().getTime();

        System.out.println(t2 - t1);
        System.out.println(maps);

        t1 = new Date().getTime();
        User user = userBiz.queryByName2("张三");
        t2 = new Date().getTime();
        System.out.println(t2 - t1);
        System.out.println(user);
    }

    @Test
    public void login() {
        User user = userBiz.queryByNameAndPwd("张三", "a");
        System.out.println(user);
        user = userBiz.queryByNameAndPwd("张三", "aa");
        System.out.println(user);
    }

    @Test
    public void delete() {
        Assert.isTrue(userBiz.delete(1));
    }

    @Test
    public void add() {
        User user = new User(null, "Test", "男", "aaaaa", "1234@qq.com");
        Assert.isTrue(userBiz.add(user));
    }
    @Test
    public void change() {
        Assert.isTrue(userBiz.change("测试",9),"修改失败");
    }

    @Test
    public void test1(){
        printList(userBiz.test1("1"));
        System.out.println();
        printList(userBiz.test1("1 and sex = '男'"));
    }
    @Test
    public void test2(){
        printList(userBiz.test2("1"));
        System.out.println();
        printList(userBiz.test2("1 and sex = '男'"));
    }

    public void printList(List list){
        for(Object o: list){
            System.out.println(o);
        }
    }
}