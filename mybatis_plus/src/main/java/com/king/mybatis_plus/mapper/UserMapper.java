package com.king.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.king.mybatis_plus.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
//表明这是一个Mapper，也可以在启动类上加上包扫描
//继承BaseMapper可以省略xml的编写
public interface UserMapper extends BaseMapper<User> {

}
