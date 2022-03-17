package com.king.security.service;

import com.king.security.entity.Role;
import com.king.security.entity.User;
import com.king.security.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 21:18
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
    public User register(String name, String pwd,String phone, Role role) {
        return userMapper.save(User.builder().name(name).phone(Long.parseLong(phone)).password(pwd).role(role).build());
    }

    /**
     * 注册 密码为加密后的
     *
     * @param name
     * @param pwd
     * @return
     */
    public User registerByEncode(String name, String pwd,String phone, Role role) {
        pwd = encoder.encode(pwd);
        return userMapper.save(User.builder().name(name).phone(Long.parseLong(phone)).password(pwd).role(role).build());

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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userMapper.findOneByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("此用户不存在");
        }
        logger.info("{}登录了", user);
        return user;
    }


}
