package com.king.oss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QiniuServiceImplTest {

    @Autowired
    private QiniuServiceImpl qiniuService;


    @Test
    public void uploadFile() {
        qiniuService.testUpload();
    }

}