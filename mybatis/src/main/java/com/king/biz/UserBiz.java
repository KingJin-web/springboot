package com.king.biz;

import com.king.bean.User;
import com.king.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: testmybits
 * @description:
 * @author: King
 * @create: 2021-07-24 12:44
 */

@Service
public class UserBiz {
    @Resource
    private UserMapper um;

    public User selectById(int id) {
        return um.selectById(id);
    }

    public List<User> queryAll() {
        return um.selectAll();
    }

    public Map<String, Object> queryByName1(String name) {
        return um.selectByName1(name);
    }

    public User queryByName2(String name) {
        return um.selectByName2(name);
    }

    public User queryByNameAndPwd(String name, String pwd) {
        return um.selectByNameAndPwd(name, pwd);
    }

    public boolean delete(int id) {
        return um.deleteById(id);
    }

    public boolean add(User user) {
        return um.insertUser(user.getName(), user.getSex(), user.getPwd(), user.getEmail());
    }

    public boolean change(String name, int id) {
        return um.updateById(name, id);
    }



    public List<User> test1(String id){
        return um.select1(id);
    }
    public List<User> test2(String id){
        return um.select2(id);
    }
}
