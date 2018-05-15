package com.artemis.lottery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhengenshen
 * @date 2018-05-15 15:44
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("足彩接口")
                .description("没有描述")
                // .termsOfServiceUrl("")
                // .contact(new Contact("csdn大师兄", "http://blog.csdn.net/qq_27093465", "cmshome@163.com"))
                // .license("")
                // .licenseUrl("")
                .version("1.0")
                .build();
    }
}
