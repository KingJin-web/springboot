package com.king.test;

import com.king.bean.Users;
import com.king.mapper.UsersMapper;

import com.king.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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