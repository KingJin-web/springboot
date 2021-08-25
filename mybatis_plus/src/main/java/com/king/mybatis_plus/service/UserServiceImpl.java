package com.king.mybatis_plus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.king.mybatis_plus.bean.User;
import com.king.mybatis_plus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-08-25 03:01
 */
@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;

    public List<User> queryAll() {
        return userMapper.selectList(null);
    }

    public User queryById(User user) {
        return userMapper.selectById(user.getId());
    }

    // 通过ID查询
    public User queryById(int id) {
        return userMapper.selectById(id);
    }

    // 通过ID删除
    public int deleteById(User user) {
        return userMapper.deleteById(user.getId());
    }

    // 删除通过ID
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    // 根据条件更新
    public void changeBy(User user, String column, Object val) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(column, val);
        int num = userMapper.update(user, userQueryWrapper);
        System.out.println("影响行数：" + num);
    }

    // 修改信息通过ID
    public void changeUserById(User user) {
        int num = userMapper.updateById(user);
        System.out.println("影响行数：" + num);
    }

    public void delete(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        int num = userMapper.delete(userQueryWrapper);
        System.out.println("影响行数：" + num);
    }
}
