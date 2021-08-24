package com.king.mapper;

import com.king.bean.Users;
import com.king.dao.UsersDaoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-08-21 00:09
 */

@Mapper
public interface UsersMapper {

    //批量插入数据方法
    @InsertProvider(type = UsersDaoProvider.class, method = "insertAll")
    void batchSaveUsersList(@Param("list") List<Users> users);

    //单条插入数据
    @Insert("insert into users(time_datetime, time_timestamp, time_long) value(#{timeDate}, #{timeTimestamp}, #{timeLong})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveUsers(Users users);
}
