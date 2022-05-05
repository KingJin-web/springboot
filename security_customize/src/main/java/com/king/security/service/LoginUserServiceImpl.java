package com.king.security.service;

import com.alibaba.fastjson.JSON;
import com.king.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 12613
 * @project: springboot
 * @pcakage: com.king.security.service.LoginUserServiceImpl
 * @date: 2022年05月05日 21:33
 * @description: ${}
 */
@Service
public class LoginUserServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SessionRegistry sessionRegistry;

    public List<User> getLoginUser() {
        List<Object> list = sessionRegistry.getAllPrincipals();
        logger.info("在线人数=[{}]", list.size());
        List<User> userList = new ArrayList<>();
        for (Object o : list) {
            if (o instanceof User) {
                User user = (User) o;
                userList.add(user);
            }
        }
        return userList;
    }
}