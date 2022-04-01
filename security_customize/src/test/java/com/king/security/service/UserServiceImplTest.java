package com.king.security.service;

import com.king.security.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Test
    public void test() {
        assertTrue(true);
    }

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void registerByEncode() {
        userService.registerByEncode("admin","aaaa", String.valueOf(12345678), Role.ADMIN);
        userService.registerByEncode("user","aaaa",String.valueOf(12345678), Role.USER);
        userService.registerByEncode("king","aaaa",String.valueOf(12345678) ,Role.TEMP);
    }
}
