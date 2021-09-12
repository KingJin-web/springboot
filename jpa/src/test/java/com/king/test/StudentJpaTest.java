package com.king.test;


import com.king.bean.Student;
import com.king.dao.StudentDao;
import com.king.util.CreatName;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-12 02:45
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentJpaTest {
    @Test
    public void test1(){
        studentDao.findByName("姬静").println();
        studentDao.findByAddressLike("%0号%").forEach(System.out::println);
        studentDao.findByIdGreaterThanAndClassNoEquals(30,8).forEach(System.out::println);
//        studentDao.findStudentById(30).println();
//        studentDao.findByAgeGreaterThan(5).forEach(System.out::println);
//        //查找
//        studentDao.findFirstByAddressLike("%0号%").forEach(System.out::println);
    }
    @Autowired
    StudentDao studentDao;
    @Test
    public void testInsert(){
        List<Student> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Student student = new Student(i, CreatName.getName(),random.nextInt(3) + 18,CreatName.name_sex,CreatName.getNum(8,10),CreatName.getTel(),CreatName.getRoad());
            list.add(student);
        }
        list.forEach(System.out::println);
       studentDao.saveAll(list);
    }




}
