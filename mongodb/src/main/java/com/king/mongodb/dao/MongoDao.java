package com.king.mongodb.dao;


import com.king.mongodb.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-12 00:55
 */

@Component
public class MongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 创建对象
     *
     * @return
     */
    public Person save(Person person) {
        return mongoTemplate.save(person);
    }

    /**
     * 根据用户名查询对象
     *
     * @return
     */
    public List<Person> findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Person.class);
    }

    public Person findById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Person.class);
    }

    /**
     * 更新对象
     *
     * @return
     */
    public Person updateById(Person person) {
        Query query = new Query(Criteria.where("id").is(person.getId()));
        Update update = new Update().set("age", person.getAge()).set("name", person.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, Person.class);
        //更新查询返回结果集的所有
        return findById(person.getId());
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public boolean deleteById(Integer id) {
        boolean b = false;
        try {
            Query query = new Query(Criteria.where("id").is(id));
            b = mongoTemplate.remove(query, Person.class).wasAcknowledged();
        } catch (Exception e) {
            return false;
        }

        return b;
    }

}
