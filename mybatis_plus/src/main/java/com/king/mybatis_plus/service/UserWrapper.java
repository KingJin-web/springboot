package com.king.mybatis_plus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.king.mybatis_plus.bean.User;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

/**
 * @program: springboot
 * @description: MyBits-pluse-QueryWrapper详解
 * @author: King
 * @create: 2021-08-30 00:08
 */
@Service

public class UserWrapper {
    private QueryWrapper<User> userQueryWrapper;

}
