package com.king.mybatis_xml.mapper;

import com.king.mybatis_xml.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-10-25 17:11
 */
@Mapper
public interface UserMapper {
    List<User> findAll();
}
