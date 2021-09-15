package com.king.controller;

import com.king.service.UsersServiceImpl;
import com.king.vo.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-09 16:32
 */
@RestController
public class UserController {
    @Autowired
    UsersServiceImpl usersService;


    @RequestMapping(value = "/query1", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel query1(int limit) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel.setMsg("查询成功");
            jsonModel.setCount(usersService.queryCursor(limit));
            jsonModel.setCode(0);
        } catch (Exception e) {
            jsonModel.setMsg(e.getMessage());
            jsonModel.setCode(1);
            e.printStackTrace();
        }

        return jsonModel;
    }

    @RequestMapping(value = "/query2", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel query2(int limit) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel.setMsg("查询成功");
            jsonModel.setCount(usersService.queryLimit(limit));
            jsonModel.setCode(0);
        } catch (Exception e) {
            jsonModel.setMsg(e.getMessage());
            jsonModel.setCode(1);
            e.printStackTrace();
        }

        return jsonModel;
    }
}
