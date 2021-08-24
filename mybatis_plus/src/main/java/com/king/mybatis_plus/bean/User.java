package com.king.mybatis_plus.bean;


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
public class User implements Serializable {
    private static final long serialVersionUID = -5644799954031156649L;
    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;

    public User() {
    }

    public User(Integer id, String name, String sex, String pwd, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.pwd = pwd;
        this.email = email;
    }

}
