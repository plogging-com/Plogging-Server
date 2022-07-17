package com.plogging.global.utill;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket restAPI() {
//        List<Parameter> global = new ArrayList<>();
//        global.add(new ParameterBuilder().
//                name("X-ACCESS-TOKEN").
//                description("X-ACCESS-TOKEN")
//                .parameterType("header").
//                required(false)
//                .modelRef(new ModelRef("string")).build());

        return new Docket(DocumentationType.SWAGGER_2)
//                .globalOperationParameters(global)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.plogging"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("plogging api sheet")
                .version("1.0.0")
                .description("ㅇㅅㅇ 화이팅!")
                .build();
    }

}
