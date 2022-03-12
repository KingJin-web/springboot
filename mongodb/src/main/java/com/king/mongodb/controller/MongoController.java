package com.king.mongodb.controller;

import com.king.mongodb.dao.MongoDao;
import com.king.mongodb.entity.Person;
import com.king.mongodb.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-12 18:31
 */
@RestController
@RequestMapping("/mongo")
@Api(value = "mongodb测试接口", tags = "mongodb测试接口")
public class MongoController {

    @Autowired
    private MongoDao mongoDao;

    @GetMapping(value = "/save.do")
    @ApiOperation(value = "新增", tags = "mongodb测试接口")
    public Person save(String name, Integer age) {
        return mongoDao.save(new Person(name, age));
    }

    @GetMapping(value = "/findByName.do")
    @ApiOperation(value = "查询", tags = "mongodb测试接口")
    public Result findByName(String name) {
        Result result = new Result();
        List<Person> list = mongoDao.findByName(name);
        result.setData(list);
        result.setMessage("查询结果");
        result.setStatus(true);
        result.setCount(list.size());
        return result;
    }

    @GetMapping(value = "/updateById.do")
    @ApiOperation(value = "修改", tags = "mongodb测试接口")
    public Person updateById(Integer id, String name, Integer age) {
        Person person = new Person(id, name, age);
        return mongoDao.updateById(person);
    }

    @GetMapping(value = "/deleteById.do")
    @ApiOperation(value = "删除", tags = "mongodb测试接口")
    public Boolean deleteById(Integer id) {
        return mongoDao.deleteById(id);
    }

}
