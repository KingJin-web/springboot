package com.king.mybatis.test;

import com.king.mybatis.bean.User;
import com.king.mybatis.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-24 16:38
 */
@SpringBootTest
public class LatchTest {


    private static final int NUM = 2; //并发数

    @Autowired
    UserServiceImpl userService;

    @Test
    public void test1() throws InterruptedException {
        Runnable taskTemp = new Runnable() {

            // 注意，此处是非线程安全的，留坑
            private int iCounter;

            @Override
            public void run() {
//                for(int i = 0; i < 10; i++) {
                // 发起请求
//                HttpUtils.sendPost("http://172.16.7.206:8085/a/actApi/integrationTodoList",
//                        "userName=gaopeng&pageNum=1&pageSize=15");
                User user = User.builder().name("King").build();
                try {
                    userService.register(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iCounter++;
                System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        LatchTest latchTest = new LatchTest();
        latchTest.startTaskAllInOnce(NUM, taskTemp);
    }

    public long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        System.err.println("=====执行时间：" + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + "豪秒");
        return endTime - startTime;
    }
}
