package com.king.other.short_link.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: springboot
 * @description: 自动填充
 * @author: King
 * @create: 2021-09-11 00:28
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info(" -------------------- start insert fill-----------------------");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);

        // 起始版本 3.3.3(推荐)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info(" -------------------- start update fill ----------------------- ");
    }
}