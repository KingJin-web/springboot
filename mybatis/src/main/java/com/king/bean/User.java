package com.king.bean;




import java.util.List;

/**
 * @program:
 * @description: 用户实体类
 * @author:
 * @create:
 */


public class User {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
