package com.king.mybatis.test;

import com.king.bean.User;
import com.king.mapper.UserMapper;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-15 11:39
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserCursorTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
        try (Cursor<User> cursor = userMapper.scan(10)) {
            cursor.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void test2() {

        try (SqlSession sqlSession = sqlSessionFactory.openSession();
             Cursor<User> cursor = sqlSession.getMapper(UserMapper.class).scan(10)) {
            cursor.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    public void test3() {
        //TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);  // 1
        transactionTemplate.execute(status -> {               // 2
            try (Cursor<User> cursor = userMapper.scan(20)) {
                cursor.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Transactional
    @Test
    public void test4() {
        try (Cursor<User> cursor = userMapper.scan(10)) {
            cursor.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
