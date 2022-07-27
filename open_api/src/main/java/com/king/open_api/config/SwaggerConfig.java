package com.king.open_api.config;


import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: King
 * @project: vibrator-spider
 * @date: 2022年07月24日 08:51
 * @description:
 */
@Configuration
public class SwaggerConfig {
    @Bean
    Docket docket() {

        return new Docket(DocumentationType.OAS_30)
                //配置网站的基本信息
                .apiInfo(new ApiInfoBuilder()
                        //网站标题
                        .title("OpenApi项目在线接口文档")
                        //标题后面的版本号
                        .version("v1.0")
                        .description("项目接口文档")
                        //联系人信息
                        .contact(new Contact("King", "http://blog.wuzhaoqi.top", "111@qq.com"))
                        .build())
                .select()
                //指定接口的位置
                .apis(RequestHandlerSelectors.basePackage("com.king.open_api.controller"))
                .build();
    }
}