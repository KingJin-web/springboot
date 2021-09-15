package com.king.bean;


import lombok.Builder;
import lombok.Data;

import java.io.Closeable;
import java.io.Serializable;
import java.util.List;

/**
 * @program:
 * @description: 用户实体类
 * @author:
 * @create:
 */

@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -7585862229833387698L;
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
