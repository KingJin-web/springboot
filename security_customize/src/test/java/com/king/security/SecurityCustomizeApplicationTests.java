package com.king.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class SecurityCustomizeApplicationTests {

    public interface QuackBehavior {
        public void quack();
    }
    public interface FlyBehavior {
        public void fly();
    }
    public abstract class Duck {
        FlyBehavior flybehavior;
        QuackBehavior quackbehavior;
        public Duck(){}

        public void quack(){

        }
        public void fly(){

        }
        public void swim(){
            System.out.println("All Duck float,even decoys");
        }

    }

}


