package com.king.mybatis_plus.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @program:
 * @description: 用户实体类
 * @author:
 * @create:
 */

@Data
@Builder
@TableName(value = "User")//指定表名
public class User implements Serializable {
    private static final long serialVersionUID = -5644799954031156649L;
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;
}
