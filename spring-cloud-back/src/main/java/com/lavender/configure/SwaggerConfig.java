package com.lavender.configure;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo(){
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder ();

        apiInfoBuilder.title ("the cloth designing API Document")
                .version ("1.0")
                .description ("the managing of department interface");

        return apiInfoBuilder.build ();


    }

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo()).select() .apis(RequestHandlerSelectors.basePackage("com.lavender.controller"))

                //为有@Api注解的Controller生成API文档
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .apis (RequestHandlerSelectors.basePackage ("com.lavender.controller"))
                .paths(PathSelectors.any())
                .build().directModelSubstitute(Timestamp.class,Long.class);
        return docket;

    }



}
