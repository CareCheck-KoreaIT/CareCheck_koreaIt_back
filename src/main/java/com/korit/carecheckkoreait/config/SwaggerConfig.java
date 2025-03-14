package com.korit.carecheckkoreait.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.info(getInfo());
        return openAPI;
    }

    private Info getInfo() {
        Info info = new Info();
        info.title("carecheck");
        info.version("1.0.0");
        info.description("api 문서");
        info.contact(getContact());
        return info;
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.name("3조");
        contact.email("tkagns2232@naver.com");
        return contact;
    }
}
