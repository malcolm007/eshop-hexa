package com.global.eshophexa.infrastructure.config;

import com.global.eshophexa.ports.incoming.CategoryUseCases;
import com.global.eshophexa.ports.outgoing.CategoryRepositoryPort;
import com.global.eshophexa.services.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    CategoryUseCases categoryUseCases(CategoryRepositoryPort categoryRepositoryPort) {
        return new CategoryService(categoryRepositoryPort);
    }
}
