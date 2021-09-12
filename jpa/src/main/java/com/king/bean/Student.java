package com.king.bean;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @program: springboot
 * @description: 学生实体类
 * @author: King
 * @create: 2021-09-12 16:09
 */

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键自增
    private String name;//姓名
    private Integer age;//年龄
    private String sex;//性别
    private Integer classNo;//班级号
    private String phoneNum;//手机号码
    private String address;//住址
    public Student()
    {
        //父类无参构造
    }

    public Student(Integer id, String name, Integer age, String sex, Integer classNo, String phoneNum, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.classNo = classNo;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public void println() {
        System.out.println(this);
    }
}