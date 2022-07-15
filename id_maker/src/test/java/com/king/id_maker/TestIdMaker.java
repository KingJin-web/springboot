package com.king.id_maker;

import com.king.id_maker.service.IdMaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Exception
 * @create 2021-07-16-16:09
 * @content
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIdMaker {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Test
    public void test() throws Exception {
        //相当于命名服务
        IdMaker idMaker = new IdMaker("/NameService/IdGen","ID");
        idMaker.start();
        try {
            for (int i = 0; i < 5; i++) {
                String id = idMaker.generateId(IdMaker.RemoveMethod.IMMEDIATELY);
                logger.info("id" + id);
            }
        } finally {
            idMaker.stop();
        }
    }
}
