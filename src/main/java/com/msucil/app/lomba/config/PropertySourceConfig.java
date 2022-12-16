package com.msucil.app.lomba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertySourceConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfig = new PropertySourcesPlaceholderConfigurer();

        placeholderConfig.setLocation(new ClassPathResource("git.properties"));
        placeholderConfig.setIgnoreResourceNotFound(true);
        placeholderConfig.setIgnoreUnresolvablePlaceholders(true);

        return placeholderConfig;
    }
}
