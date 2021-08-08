package com.king.dao;

import com.king.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: springboot
 * @description:  JpaRepository接口
 * @author: King
 * @create: 2021-08-08 03:11
 */

public interface UserDao extends  JpaRepository<User, Integer> {
//    List<User> findUserById(Integer id);
//
//    //方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
//    List<User> findByName(String name);
//
//    List<User> findByNameLike(String name);
//
//    @Query(value = "select * from user where name= ?")
//    List<User> queryAllByName(String name);
}
