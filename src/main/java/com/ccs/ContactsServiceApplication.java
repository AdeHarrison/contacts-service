package com.ccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableSwagger2
public class ContactsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactsServiceApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET,
                        asList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("An unexpected error occurred")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("The specified resource could not be found")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("Something went wrong due to a bad request from the client")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(200)
                                        .message("Successful response")
                                        .build()));
    }
}