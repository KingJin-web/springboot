package com.king.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.king.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
//表明这是一个Mapper，也可以在启动类上加上包扫描
//Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
public interface UserMapper extends BaseMapper<User> {
    /**
     * <p>
     * 查询 : 根据性别查询用户列表，分页显示
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param sex 性别
     * @return 分页对象
     */
    @Select("SELECT * FROM user WHERE sex = #{sex}")
    IPage<User> selectPageBySex(Page<?> page, String sex);

}

