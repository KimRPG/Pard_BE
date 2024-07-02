package com.pard.pard_backend.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        Server server = new Server();
        server.setUrl("https://we-pard.store");
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .servers(List.of(server));

    }

    private Info apiInfo(){
        return new Info()
                .title("PARD APP")
                .description("PARD APP API Documentation")
                .version("1.0.0");
    }
}
