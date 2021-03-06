package com.king.dao;

import com.king.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @program: springboot
 * @description: JpaRepository接口
 * @author: King
 * @create: 2021-08-08 03:11
 */
public interface UserDao extends JpaRepository<User, Integer> {

    //这里使用默认的字段拼接形成的方法名，从而自动解析形成对应的方法。
    //这个方法的意思是通过id降序排序
    Page<User> findByOrderByIdDesc(Pageable pageable);



    @Query("from User u where u.name = ?1 and u.id > ?2")
    List<User> queryByName(String name, int id);

    @Query("from User u where u.name=:name")
    User queryUser1(@Param("name") String name);

    @Query("from User u where u.name like %?1")
    List<User> queryUserLike1(@Param("name") String name);

    @Query("from User u where u.name like %:name%")
    List<User> queryUserLike2(@Param("name") String name);

    @Query("select count(u.sex) from User u group by u.sex")
    List<Integer> querySexNum();

    @Query("select count(u.name)  from User u group by u.sex")
    List<Integer> query();

    //方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
    List<User> findByName(String name);

    User findUserById(Integer id);

    List<User> findByNameLike(String name);



    @Query("select u from User u ORDER BY u.name desc ")
    Page<User> findInOrders(Pageable pageable);
}
