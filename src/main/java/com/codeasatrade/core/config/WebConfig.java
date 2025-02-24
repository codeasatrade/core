package com.codeasatrade.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    
    @Value("${cors.allowed.origins}")
    private String[] allowedOrigins;


    // @Profile("dev")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
            registry.addMapping("/**")
            .allowedOrigins(allowedOrigins);
        
    }
    
}
