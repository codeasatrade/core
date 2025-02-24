package com.codeasatrade.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Your API Title",
        version = "1.0.0",
        description = "API Description"
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local server"),
        @Server(url = "https://api.example.com", description = "Production server")
    }
)
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
