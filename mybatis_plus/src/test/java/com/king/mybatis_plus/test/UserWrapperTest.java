package com.king.mybatis_plus.test;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.king.mybatis_plus.bean.User;

import com.king.mybatis_plus.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import com.king.mybatis_plus.bean.User;
import com.king.mybatis_plus.service.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

//https://www.jianshu.com/p/a4d5d310daf8
@SpringBootTest
@SuppressWarnings("unchecked")
public class UserWrapperTest {


    @Autowired
    UserMapper userMapper;

    AbstractWrapper wrapper;

    @Before
    public void before() {

    }

    @After
    public void after() {
        System.out.println("查询全部");
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void query() {
        //TODO allEq

        wrapper = new QueryWrapper<User>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("sex", "男");
        map.put("name", null);
        wrapper.allEq(map);
        userMapper.selectList(wrapper).forEach(System.out::println);

        //params : key为数据库字段名,value为字段值
        //null2IsNull : 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
        //filter : 过滤函数,是否允许字段传入比对条件中
        //params 与 null2IsNull : 同上
        wrapper = new QueryWrapper<User>();

        wrapper.allEq(map, false);
        userMapper.selectList(wrapper).forEach(System.out::println);


    }

    @Test
    public void testEq() {
        //TODO eq
        //等于 =
        //例: eq("name", "老王")--->name = '老王'
        wrapper = new QueryWrapper<User>();
        wrapper.eq("sex", "男");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void testNe() {
        //TODO ne
        //不等于 <>
        //例: ne("name", "老王")--->name <> '老王'
        wrapper = new QueryWrapper<User>();
        wrapper.ne("sex", "男");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void testGt() {
        //大于 >
        //例: gt("age", 18)--->age > 18
        wrapper = new QueryWrapper<User>();
        wrapper.gt("id", 10);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void testGe() {
        // 大于等于 >=
        // 例: ge("age", 18)--->age >= 18
        wrapper = new QueryWrapper<User>();
        wrapper.ge("id", 10);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void testLt() {
        // 小于 <
        //例: lt("age", 18)--->age < 18
        wrapper = new QueryWrapper<User>();
        wrapper.lt("id", 10);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void testLe() {
        //小于等于 <=
        //例: le("age", 18)--->age <= 18
        wrapper = new QueryWrapper<User>();
        wrapper.le("id", 10);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void deleteById() {

    }

    @Test
    public void testDeleteById() {
    }
}