package com.king.security.mapper;

import com.king.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-25 03:43
 */

public interface UserMapper  extends JpaRepository<User, Integer> {
    User findOneByName(String name);
}
