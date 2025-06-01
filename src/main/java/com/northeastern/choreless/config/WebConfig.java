package com.northeastern.choreless.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve anything under "/css/**" from "/webapp/css/"
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        // Serve anything under "/images/**" from "/webapp/images/"
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("/images/");
    }

    // (You can add more MVC configuration methods here if needed)
}

