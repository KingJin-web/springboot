package com.king.controller;

import com.king.bean.Users;
import com.king.mapper.UsersMapper;
import com.king.vo.JsonModel;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-09 16:32
 */
@RestController
public class UsersController {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "/query1", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel query1(int limit) {
        JsonModel jsonModel = new JsonModel();
        try (SqlSession sqlSession = sqlSessionFactory.openSession();

        ) {
            long start = new Date().getTime();
            Cursor<Users> cursor = sqlSession.getMapper(UsersMapper.class).scan(limit);
            long end = new Date().getTime();
            cursor.forEach(System.out::println);
            jsonModel.setMsg("查询成功! " + "耗时: " + (end - start) );
            cursor.close();
            return jsonModel;
        } catch (Exception e) {
            jsonModel.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return jsonModel;
    }

    @RequestMapping(value = "/query2", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel query2(int limit) {
        JsonModel jsonModel = new JsonModel();
        try {
            long start = new Date().getTime();
            List<Users> list = usersMapper.selectList(limit);
            long end = new Date().getTime();
            list.forEach(System.out::println);
            jsonModel.setMsg("查询成功! " + "耗时: " + (end - start) );
        } catch (Exception e) {
            jsonModel.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return jsonModel;
    }
}
