package com.king.mongodb.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-10-10 22:33
 */
@Configuration
@EnableSwagger2  //启用swaggerr 注解解析器
@EnableKnife4j //启用knife4j增强
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    // 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enable}")  //通过 @Value  获取配置信息
    // 复习@Environement  @Value    @ConfigurationProperties
    private Boolean swaggerEnable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(HttpSession.class, HttpServletRequest.class)  //在生成的文档将哪些类对象的属性排除
                // 是否开启
                .enable(swaggerEnable)
                .select()
                // 扫描的路径包,只要这些包中的类配有swagger注解，则启用这些注解
                .apis(RequestHandlerSelectors.basePackage("com.king"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试平台-平台管理API文档")
                .description("平台管理服务api")
                // 作者信息
                .contact(new Contact("king", "http://www.huahuazhw.xyz", "824276226@qq.com"))
                .version("1.0.0")
                .build();
    }
}


