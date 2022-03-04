package com.king.security.service;

import com.king.security.entity.Role;
import com.king.security.entity.User;
import com.king.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-02 21:18
 */
@Service
public class UserServiceImpl implements UserDetailsService {
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
    public User register(String name, String pwd, Role role) {
        if (isUserName(name)) {
            System.out.println("此昵称已经被占用");
            return null;
        }
        return userMapper.save(User.builder().name(name).password(pwd).role(role).build());
    }

    /**
     * 注册 密码为加密后的
     *
     * @param name
     * @param pwd
     * @return
     */
    public User registerByEncode(String name, String pwd, Role role) {
        if (isUserName(name)) {
            System.out.println("此昵称已经被占用");
            return null;
        }
        pwd = encoder.encode(pwd);
        return userMapper.save(User.builder().name(name).password(pwd).role(role).build());
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
        if (user == null){
            throw new UsernameNotFoundException("此用户不存在");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getText());
        grantedAuthorities.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(name,
                user.getPassword(), grantedAuthorities);
    }


}
