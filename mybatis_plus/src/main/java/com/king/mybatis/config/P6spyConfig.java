//package com.king.mybatis_plus.config;
//
///**
// * @program: springboot
// * @description:
// * @author: King
// * @create: 2021-09-08 17:46
// */
//
//import com.p6spy.engine.spy.P6DataSource;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//
//import javax.sql.DataSource;
//
///**
// * 对数据源进行封装，打印运行sql
// */
//@Configuration
//public class P6spyConfig {
//
//    static class P6DataSourceBeanPostProcessor implements BeanPostProcessor, Ordered {
//
//        @Override
//        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//            if (bean instanceof DataSource) {
//                return new P6DataSource((DataSource) bean);
//            }
//            return bean;
//        }
//
//        @Override
//        public int getOrder() {
//            return Ordered.LOWEST_PRECEDENCE;
//        }
//    }
//
//    @Bean
//    public P6DataSourceBeanPostProcessor p6DataSource() {
//        return new P6DataSourceBeanPostProcessor();
//    }
//
//
//}