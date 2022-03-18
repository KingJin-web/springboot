package com.king.security.service;

import com.king.security.util.MyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TxSmsServiceImplTest  {
    @Autowired
    private TxSmsServiceImpl txSmsService;
    @Test
    public void sendSms() throws MyException {
        txSmsService.sendSmsCode("19973653797","123456");
    }

}