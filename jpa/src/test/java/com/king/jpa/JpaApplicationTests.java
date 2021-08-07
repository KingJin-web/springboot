package com.king.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
public class JpaApplicationTests {

    @Test
    void contextLoads() {
    }

    static class Threads4 {
        public static void main(String[] args) {
            new Threads4().go();
        }

        public void go() {
            Runnable r = new Runnable() {
                public void run() {
                    System.out.print("foo");
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

    static class A{
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String s = in.next();

            String[] sr = s.split("\\.");
            int b =Integer.parseInt(sr[1]) ;
            System.out.println(b);

        }
    }
}
