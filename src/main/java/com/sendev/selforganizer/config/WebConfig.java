package com.sendev.selforganizer.config;

import com.sendev.selforganizer.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private ApplicationProperties.Cors cors;

    public WebConfig(@Autowired ApplicationProperties applicationProperties) {
        this.cors = applicationProperties.getCors();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(cors.getUiOrigin())
                .allowCredentials(cors.getAllowCredentials())
                .maxAge(cors.getMaxAge());
    }

}
