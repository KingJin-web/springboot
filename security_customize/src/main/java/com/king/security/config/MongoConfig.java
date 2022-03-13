package com.king.security.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-02-12 00:07
 */
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    public String getDatabaseName() {
        return "java_test";//数据库名
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        List<ServerAddress> hosts = new ArrayList<>();
        //参数为 数据库地址 端口号有多个可以添加
        hosts.add(new ServerAddress("127.0.0.1", 27017));
        //参数为 用户名 数据库名 数据库密码
        builder.credential(MongoCredential.createCredential("king", getDatabaseName(), "123456".toCharArray()))
                .applyToClusterSettings(settings -> {
                    settings.hosts(hosts);
                });
    }


    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory databaseFactory,
                                                       MongoCustomConversions customConversions,
                                                       MongoMappingContext mappingContext) {
        MappingMongoConverter mmc = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext);
        mmc.setTypeMapper(defaultMongoTypeMapper());
        return mmc;
    }

    @Bean
    public MongoTypeMapper defaultMongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }

}
