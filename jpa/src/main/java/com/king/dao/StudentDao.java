package com.king.dao;

import com.king.bean.Student;
import com.king.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-12 16:10
 */
public interface StudentDao extends JpaRepository<Student, Integer> {

    Student findByName(String name);
    //方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
    List<Student> findByAddressLike(String address);

    List<Student> findByIdGreaterThanAndClassNoEquals(int id,int classNo);
    Student findStudentById(int id);

    //查询 年龄大于 a 的学生
    List<Student> findByAgeGreaterThan(int a);
    //
    List<Student> findFirstByAddressLike(String address);

    @Query("from Student s where s.name=:name")
    List<Student> findStudent(@Param("name") String name);

}
