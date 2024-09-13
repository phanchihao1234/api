package com.example.spring_api.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${open.api.title}")
    private String title;
    @Value("${open.api.version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description("Hôm nay trời đẹp")
                        .license(new License().name("Spring boot").url("https://spring.io/quickstart"))
                ).servers(List.of(new Server().url("localhost:8080/").description("server nè")));
    }

    @Bean
//    @Value()
    public GroupedOpenApi publicApi1(){
        return GroupedOpenApi.builder()
                .group("Admin-api")
                .pathsToMatch("/api/**")
                .build();

    }
    @Bean
    public GroupedOpenApi publicApi2(){
        return GroupedOpenApi.builder()
                .group("Student-api")
                .pathsToMatch("/api/**")
                .build();
    }

}
