package com.king.security.entity;


import lombok.Builder;
import lombok.Data;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.persistence.*;
import javax.persistence.criteria.Root;

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
    //角色 这个注解表示使用枚举类中的int
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public User() {

    }

    //@builder默认用的是全参数构造函数  没有这个会报错 也可以加@AllArgsConstructor
    public User(Long id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
