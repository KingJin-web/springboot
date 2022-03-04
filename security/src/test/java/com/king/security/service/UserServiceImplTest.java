package com.king.security.service;

import com.king.security.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void register() {
        userService.register("admin","aaaa", Role.ADMIN);
        userService.register("user","aaaa", Role.USER);
        userService.register("king","aaaa", Role.TEMP);
    }

    @Test
    public void registerByEncode() {
        userService.registerByEncode("admin1","aaaa", Role.ADMIN);
        userService.registerByEncode("user1","aaaa", Role.USER);
        userService.registerByEncode("king1","aaaa", Role.TEMP);
    }

}