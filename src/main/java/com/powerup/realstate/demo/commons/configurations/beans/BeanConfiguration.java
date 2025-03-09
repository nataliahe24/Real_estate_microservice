package com.example.demo.commons.configurations.beans;

import com.example.demo.category.domain.ports.in.CategoryServicePort;
import com.example.demo.category.domain.ports.out.CategoryPersistencePort;
import com.example.demo.category.domain.usecases.CategoryUseCase;
import com.example.demo.category.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.example.demo.category.infrastructure.mappers.CategoryEntityMapper;
import com.example.demo.category.infrastructure.repositories.mysql.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public CategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }
}
