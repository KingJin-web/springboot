package com.king.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-25 03:41
 */
@Data
@TableName(value = "user")//指定表
@Builder
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_ID)//指定自增策略
    private Long id;
    private String name;
    //密码
    private String password;

}
