package com.king.security.service;

import com.king.security.entity.User;
import com.king.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 21:18
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private Example<User> em;

    /**
     * 注册用户
     *
     * @param name
     * @param pwd
     * @return
     */
    public User register(String name, String pwd) {
        return userMapper.save(User.builder().name(name).password(pwd).build());
    }

    /**
     * 注册 密码为加密后的
     *
     * @param name
     * @param pwd
     * @return
     */
    public User registerByEncode(String name, String pwd) {
        pwd = encoder.encode(pwd);
        return userMapper.save(User.builder().name(name).password(pwd).build());
    }

    /**
     * 判断用户名是否被使用
     *
     * @param name
     * @return
     */
    public boolean isUserName(String name) {

        em = Example.of(User.builder().name(name).build());
        return userMapper.findAll(em).size() >= 1;
    }


    public boolean login(String name,String pwd) {

        em = Example.of(User.builder().name(name).password(pwd).build());
        List<User> users = userMapper.findAll(em);
        if (users.size() == 0) {
            return false;
        }
        User user1 = users.get(0);

        return true;
//        return encoder.matches(pwd, user1.getPwd());
    }

}
