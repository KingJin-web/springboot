package com.king.biz;

import com.king.bean.User;

import com.king.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-08-08 12:41
 */

@Service
public class UserBiz {
    @Autowired
    UserDao userDao;

    Example<User> example;

    public List<User> selectAll() {
        return userDao.findAll();
    }

//    public List<User> selectById(Integer id) {
//       userDao.findAll(example,)
//    }
}

