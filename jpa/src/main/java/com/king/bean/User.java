package com.king.bean;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


//@Entity注解标识了User类是一个持久化的实体
//@Data和@NoArgsConstructor是Lombok中的注解。用来自动生成各参数的Set、Get函数以及不带参数的构造函数。
// 如果您对Lombok还不了解，可以看看这篇文章：Java开发神器Lombok的使用与原理
//@Id和@GeneratedValue用来标识User对应对应数据库表中的主键
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;
    private Integer version;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer flag;

    public User() {
    }

    public User(Integer id, String name, String sex, String pwd, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.pwd = pwd;
        this.email = email;

    }

    public User(Integer id, String name, String sex, String pwd, String email, Integer version,
                Timestamp createTime, Timestamp updateTime, Integer flag) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.pwd = pwd;
        this.email = email;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.flag = flag;
    }

    public void println() {
        System.out.println(this);
    }

}
