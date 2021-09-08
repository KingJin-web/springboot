package com.king.mybatis_plus.test;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.king.mybatis_plus.bean.User;

import com.king.mybatis_plus.mapper.UserMapper;
import com.sun.javafx.collections.MappingChange;
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

    @Test
    public void TestQueryWrapper() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", "test");
        userQueryWrapper.eq("sex", "男");
        userQueryWrapper.between("id", 1, 30);
        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }

    @Test
    public void TestLambdaQueryWrapper() {
        LambdaQueryWrapper<User> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.like(User::getName, "test");
        lambdaWrapper.eq(User::getSex, "男");
        lambdaWrapper.between(User::getId, 1, 30);
        userMapper.selectList(lambdaWrapper).forEach(System.out::println);
    }

    @Test
    public void TestUpdateWrapper() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        //继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件
        //及 LambdaUpdateWrapper, 可以通过 new UpdateWrapper().lambda() 方法获取!
        User user = User.builder().build();
        //修改语句
        updateWrapper.set("name", "test1");
        //条件
        updateWrapper.like("name", "test1");
        userMapper.update(user, updateWrapper);
    }

    @Test
    public void TestPage(){
        //配置了分页插件后，还是和以前一样的使用selectpage方法，
        //但是现在就是真正的物理分页了，sql语句中有limit了
        Page<User> page = new Page<>(1, 2);
        IPage<User> selectPage = userMapper.selectPage(page,null);
        System.out.println(selectPage);
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + selectPage.getTotal());
        System.out.println("当前页码:" + selectPage.getCurrent());
        System.out.println("总页数:" + selectPage.getPages());
        System.out.println("每页显示条数:" + selectPage.getSize());
//        System.out.println("是否有上一页:" + selectPage.hasPrevious());
//        System.out.println("是否有下一页:" + selectPage.hasNext());
        //还可以将查询到的结果set进page对象中
       // page.setRecords((List<User>) employeeList);
    }

    @Test
    public void TestLambdaUpdateWrapper() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        //LambdaUpdateWrapper<User> updateWrapper = new UpdateWrapper<User>().lambda();
        User user = User.builder().build();
        //修改语句
        updateWrapper.set(User::getName, "test1.1");
        //条件
        updateWrapper.like(User::getName, "test1");
        userMapper.update(user, updateWrapper);
    }

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
    public void testBetween() {
        //BETWEEN 值1 AND 值2
        //例: between("age", 18, 30)--->age between 18 and 30
        wrapper = new QueryWrapper<User>();
        wrapper.between("id", 1, 9);
        userMapper.selectList(wrapper).forEach(System.out::println);

        //SELECT id,name,sex,pwd,email FROM User WHERE (id BETWEEN ? AND ?)
    }

    @Test
    public void testNoBetween() {
//        NOT BETWEEN 值1 AND 值2
//        例: notBetween("age", 18, 30)--->age not between 18 and 30
        wrapper = new QueryWrapper<User>();
        wrapper.notBetween("id", 1, 9);
        userMapper.selectList(wrapper).forEach(System.out::println);
        //SELECT id,name,sex,pwd,email FROM User WHERE (id NOT BETWEEN ? AND ?)
    }

    @Test
    public void testLike() {


//        LIKE '%值%'
//        例: like("name", "王")--->name like '%王%
        wrapper = new QueryWrapper<User>();
        wrapper.like("name", "test");
        userMapper.selectList(wrapper).forEach(System.out::println);

//        NOT LIKE '%值%'
//        例: notLike("name", "王")--->name not like '%王%'
        wrapper = new QueryWrapper<User>();
        wrapper.notLike("name", "test2");
        userMapper.selectList(wrapper).forEach(System.out::println);

//        LIKE '值%'
//        例: likeRight("name", "王")--->name like '王%'
        wrapper = new QueryWrapper<User>();
        wrapper.likeRight("name", "test2");
        userMapper.selectList(wrapper).forEach(System.out::println);

//        LIKE '%值'
//        例: likeLeft("name", "王")--->name like '%王'
        wrapper = new QueryWrapper<User>();
        wrapper.likeLeft("name", "test2");
        userMapper.selectList(wrapper).forEach(System.out::println);

    }

    @Test
    public void testUpdateWrapper() {
        //UpdateWrapper
        //说明:
        //
        //继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件
        //及 LambdaUpdateWrapper, 可以通过 new UpdateWrapper().lambda() 方法获取!
        User user = User.builder().build();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        //修改语句
        updateWrapper.set("name", "test1");
        //条件
        updateWrapper.like("name", "test1");
        //自定义sql
        updateWrapper.setSql("id = 2");
        userMapper.update(user, updateWrapper);
    }

    @Test
    public void lambdaWrapper() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //lambdaQueryWrapper.eq(user -> user.getName(),"test1");
        lambdaQueryWrapper.eq(User::getName, "test1");
        userMapper.selectList(lambdaQueryWrapper).forEach(System.out::println);
    }

    @Test
    public void testDeleteById() {
    }
}