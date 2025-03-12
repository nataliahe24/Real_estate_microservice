package com.powerup.realestate.commons.configurations.beans;

import com.powerup.realestate.category.domain.ports.in.CategoryServicePort;
import com.powerup.realestate.category.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.category.domain.usecases.CategoryUseCase;
import com.powerup.realestate.category.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.powerup.realestate.category.infrastructure.mappers.CategoryEntityMapper;
import com.powerup.realestate.category.infrastructure.repositories.mysql.CategoryRepository;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.location.domain.usecases.LocationUseCase;
import com.powerup.realestate.location.infrastructure.adapters.persistence.LocationPersistenceAdapter;
import com.powerup.realestate.location.infrastructure.mappers.LocationEntityMapper;
import com.powerup.realestate.location.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final LocationRepository locationRepository;
    private final LocationEntityMapper locationEntityMapper;

    @Bean
    public CategoryServicePort categoryServicePort() {

        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public LocationUseCase locationServicePort() {
        return new LocationUseCase(locationPersistencePort());
    }

    @Bean
    public LocationPersistencePort locationPersistencePort() {
        return new LocationPersistenceAdapter(locationRepository, locationEntityMapper);
    }
}