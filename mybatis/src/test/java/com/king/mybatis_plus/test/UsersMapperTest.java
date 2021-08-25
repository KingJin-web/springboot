package com.king.mybatis_plus.test;

import com.king.bean.Users;
import com.king.mapper.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UsersMapperTest {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void saveAllUsers() {
        for (int a = 0;a <500;a++) {
            List<Users> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                long time = System.currentTimeMillis();
                Users users = Users.builder().timeDatetime(new Timestamp(time)).timeLong(time).timeTimestamp(new Timestamp(time)).build();
                list.add(users);
            }
            usersMapper.batchSaveUsersList(list);
        }
    }
    @Test
    public void saveUsers() {
        for (int i = 0; i < 500000; i++) {
            long time = System.currentTimeMillis();
            usersMapper.saveUsers(Users.builder().timeDatetime(new Timestamp(time)).timeLong(time).timeTimestamp(new Timestamp(time)).build());
        }
    }

}