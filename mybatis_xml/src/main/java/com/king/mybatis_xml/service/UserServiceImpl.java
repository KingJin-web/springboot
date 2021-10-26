package com.king.mybatis_xml.service;

import com.king.mybatis_xml.bean.User;
import com.king.mybatis_xml.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-10-25 17:17
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }
}
