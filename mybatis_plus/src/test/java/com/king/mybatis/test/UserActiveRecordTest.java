package com.king.mybatis.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.king.mybatis.bean.User;
import org.junit.jupiter.api.Test;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-09 18:44
 */

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserActiveRecordTest {
    @Test
    public void testInsert() {
        User user = User.builder().name("张三").sex("男").pwd("bbb").email("111@qq.com").build();
        boolean b = user.insert();
        System.out.println(b);
        //user.insertOrUpdate();
    }

    @Test
    public void testSelect() {
        User user = User.builder().build();
        //查询全部
        user.selectAll().forEach(System.out::println);
        //通过id查询
        user.selectById(65).println();
        //通过id查询
        user.setId(65);
        user.selectById();
        //分页查询
        IPage<User> page = user.selectPage(new Page<User>(2, 10), new QueryWrapper<User>().like("name", "test"));
        page.getRecords().forEach(System.out::println);
        //查询总数
        user.selectCount(null);
        // 条件查询总记录数
        user.selectList(new QueryWrapper<>());
        //查询一条记录
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 2);
        user.selectOne(queryWrapper);
    }

    @Test
    public void t(){
        User user = User.builder().build();
        //分页查询
        IPage<User> page1 = new Page<>(2, 10);
        IPage<User> page = user.selectPage(page1, new QueryWrapper<User>().like("name", "test"));
        page.getRecords().forEach(System.out::println);
        page1.getRecords().forEach(System.out::println);
    }
    @Test
    public void testUpdate() {
        User user = User.builder().build();
        //获取id为2的用户信息
        user = user.selectById(2);
        user.setName("李四1");
        //通过id修改用户
        user.updateById();
//        //条件修改
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        //修改语句
//        updateWrapper.set("name", "李四");
//        //条件
//        updateWrapper.like("name", "刘亦菲");
//        user.update(updateWrapper);
    }

    @Test
    public void insertOrUpdate(){
        User user = User.builder().id(50).name("张三").sex("男").pwd("bbb").email("111@qq.com").build();
        user.insertOrUpdate();
    }

    @Test
    public void testDelete() {
        User user = User.builder().build();
        //通过id删除
        user.deleteById(59);
        //通过id删除
        user.setId(60);
        user.deleteById();
        //条件删除
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 61);
        user.delete(queryWrapper);
    }
}
