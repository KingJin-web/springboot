package com.king.security.entity;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-25 03:41
 */
@Entity
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//主键自增
    private String name;
    //密码
    private String password;
    //角色
    private Integer role;

    public User() {

    }
}
