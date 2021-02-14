package com.lym.core.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liuyanmin on 2019/9/12.
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable}")
public class Swagger2Config {

    @Value("${swagger.host}")
    private String host;

    @Bean
    public Docket createBusinessRestApi() {

        ApiInfo businessApiInfo = new ApiInfoBuilder()
                .title("Fundf10")
                .description("基金数据爬取")
                .version("1.0.0")
                .build();

        Set<String> protocols = new HashSet<>();
        protocols.add("http");
        protocols.add("https");

        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .protocols(protocols)
                .groupName("基金数据爬取")
                .apiInfo(businessApiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lym.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
