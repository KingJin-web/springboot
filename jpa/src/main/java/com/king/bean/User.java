package com.king.bean;


import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program:
 * @description: 用户实体类
 * @author:
 * @create:
 */


//@Entity注解标识了User类是一个持久化的实体
//@Data和@NoArgsConstructor是Lombok中的注解。用来自动生成各参数的Set、Get函数以及不带参数的构造函数。
// 如果您对Lombok还不了解，可以看看这篇文章：Java开发神器Lombok的使用与原理
//@Id和@GeneratedValue用来标识User对应对应数据库表中的主键
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;


    //打印这个对象
    public void println() {
        System.out.println(this);
    }
}
