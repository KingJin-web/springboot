package com.king.mapper;

import com.king.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * @program: testmybits
 * @description:
 * @author: King
 * @create: 2021-07-24 11:32
 */

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where id = #{id}")
    User selectById(@Param("id") int id);

    @Select("select * from user where name = #{name}")
    Map<String, Object> selectByName1(@Param("name") String name);

    @Select("select * from user where name = #{name}")
    User selectByName2(@Param("name") String name);

//    @Select("select * from user where name = #{name} and pwd = #{name}")
//    User selectByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);

    @Select("select * from user where name = #{name} and pwd = #{pwd}")
    User selectByNameAndPwd(String name, String pwd);

    @Delete("delete from user where id = #{id}")
    boolean deleteById(int id);

    @Insert("insert into user values (null,#{name},#{sex},#{pwd},#{email})")
    boolean insertUser(String name, String sex, String pwd, String email);

    @Update("update user set name =  #{name} where id = #{id}")
    boolean updateById(String name, int id);


    /**
     * 使用 #{} 占位符
     *
     * @param id
     * @return
     */
    @Select("select * from user where id > #{id}")
    List<User> select1(String id);

    /**
     * 使用  ${} 拼接符
     *
     * @param id
     * @return
     */
    @Select("select * from user where id > ${id}")
    List<User> select2(String id);
}
