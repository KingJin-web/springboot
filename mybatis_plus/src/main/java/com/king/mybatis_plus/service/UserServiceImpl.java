package com.king.mybatis_plus.service;

import com.king.mybatis_plus.bean.User;
import com.king.mybatis_plus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        return userList;
    }

    public User queryById(User user) {
        return userMapper.selectById(user.getId());
    }

    public int deleteById(User user) {
        return userMapper.deleteById(user.getId());
    }
}
