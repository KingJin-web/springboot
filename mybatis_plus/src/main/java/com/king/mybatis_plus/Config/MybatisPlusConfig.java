package com.king.mybatis_plus.Config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-08 13:18
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     * 3.4.3不用设置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor1() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }

    //原本的性能分析插件自3.2.0后移除
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor2(){
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        // maxTime 指的是 sql 最大执行时长
//        performanceInterceptor.setMaxTime(5000);
//        //SQL是否格式化 默认false
//        performanceInterceptor.setFormat(true);
//        interceptor.addInnerInterceptor(new PerformanceInterceptor());
//    }


    //BlockAttackInnerInterceptor
    //针对 update 和 delete 语句 作用: 阻止恶意的全表更新删除
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor3() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 执行分析插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}