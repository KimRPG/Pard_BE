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
        Server server2 = new Server();
        server2.setUrl("http://localhost:8080");
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .servers(List.of(server,server2));

    }

    private Info apiInfo(){
        return new Info()
                .title("PARD APP")
                .description("PARD APP API Documentation")
                .version("1.0.0");
    }
}
