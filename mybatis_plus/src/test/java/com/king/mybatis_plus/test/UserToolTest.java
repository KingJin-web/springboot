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

@SpringBootTest

public class UserToolTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void Test1() {
        IPage<User> userIPage = userMapper.selectPage(new Page<>(1, 10), null);
        System.out.println(userIPage.getRecords().toString());
    }

    @Test
    public void Test2() {
        //配置了分页插件后，使用selectpage方法，
        //但是现在就是真正的物理分页了，sql语句中有limit了
        Page<User> page = new Page<>(2, 10);
        IPage<User> selectPage = userMapper.selectPage(page, null);
        System.out.println(selectPage);
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + selectPage.getTotal());
        System.out.println("当前页码:" + selectPage.getCurrent());
        System.out.println("总页数:" + selectPage.getPages());
        System.out.println("每页显示条数:" + selectPage.getSize());
        System.out.println("是否有上一页:" + page.hasPrevious());
        System.out.println("是否有下一页:" + page.hasNext());
        System.out.println("查询结果:");
        List<User> list = selectPage.getRecords();
        list.forEach(o -> System.out.println(o));
//        page.getRecords().forEach(o -> System.out.println(o));
        //还可以将查询到的结果set进page对象中
        page.setRecords(list);
        //也可以通过page调用相关方法获取到相关的分页信息，而且还可以把查询到的结果set回page对象中，方便前端使用。
    }

    @Test
    public void Test3() {
        Page<User> page = new Page<>(2, 10);
        IPage<User> selectPage = userMapper.selectPageBySex(page, "男");
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + selectPage.getTotal());
        System.out.println("当前页码:" + selectPage.getCurrent());
        System.out.println("总页数:" + selectPage.getPages());
        System.out.println("每页显示条数:" + selectPage.getSize());
        System.out.println("是否有上一页:" + page.hasPrevious());
        System.out.println("是否有下一页:" + page.hasNext());
        System.out.println("分页结果");
        selectPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void Test4(){
        //条件为null，就是删除全表，执行分析插件会终止该操作
        userMapper.delete(null);
    }
}
