package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer { // конфигурация нашего вэб слоя

    public void addViewControllers( ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");
    }

}
