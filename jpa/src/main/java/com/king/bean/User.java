package com.king.bean;



import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


/**
 * @program:
 * @description: 用户实体类
 * @author:
 * @create:
 */



@Data
@Entity//实体类
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;

    public User() { }

    public User(Integer id, String name, String sex, String pwd, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.pwd = pwd;
        this.email = email;
    }

}
