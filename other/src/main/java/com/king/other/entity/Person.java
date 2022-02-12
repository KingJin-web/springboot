package com.king.other.entity;

import lombok.Data;

import java.util.Random;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-12 00:38
 */
@Data
public class Person {

    private Integer id;
    private String name;
    private Integer age;

    public Person(String name, int age) {
        this.id = new Random().nextInt();
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public Person(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}