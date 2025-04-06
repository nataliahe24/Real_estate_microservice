package com.powerup.realestate.commons.configurations.beans;

import com.powerup.realestate.location.domain.ports.in.CityServicePort;
import com.powerup.realestate.location.domain.ports.out.CityPersistencePort;
import com.powerup.realestate.location.domain.usecases.CityUseCase;
import com.powerup.realestate.location.infrastructure.adapters.persistence.CityPersistenceAdapter;
import com.powerup.realestate.location.infrastructure.repositories.mysql.CityRepository;
import com.powerup.realestate.properties.domain.ports.in.CategoryServicePort;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.usecases.CategoryUseCase;
import com.powerup.realestate.properties.domain.usecases.PropertyUseCase;
import com.powerup.realestate.properties.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.powerup.realestate.properties.infrastructure.adapters.persistence.PropertyPersistenceAdapter;
import com.powerup.realestate.properties.infrastructure.mappers.CategoryEntityMapper;
import com.powerup.realestate.properties.infrastructure.mappers.PropertyEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.CategoryRepository;
import com.powerup.realestate.location.domain.ports.in.LocationServicePort;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.location.domain.usecases.LocationUseCase;
import com.powerup.realestate.location.infrastructure.adapters.persistence.LocationPersistenceAdapter;
import com.powerup.realestate.location.infrastructure.mappers.LocationEntityMapper;
import com.powerup.realestate.location.infrastructure.repositories.mysql.LocationRepository;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.PropertyRepository;
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
    private final CityRepository cityRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyEntityMapper propertyEntityMapper;


    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort() {

        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public LocationPersistencePort locationPersistencePort() {
        return new LocationPersistenceAdapter(locationRepository, locationEntityMapper);
    }

    @Bean
    public LocationServicePort locationServicePort(LocationPersistencePort locationPersistencePort, CityServicePort cityServicePort) {

        return new LocationUseCase(locationPersistencePort(), cityServicePort(cityPersistencePort()));
    }

    @Bean
    public CityPersistencePort cityPersistencePort() {
        return new CityPersistenceAdapter(cityRepository);
    }

    @Bean
    public CityServicePort cityServicePort(CityPersistencePort cityPersistencePort) {
        return new CityUseCase(cityPersistencePort);
    }

    @Bean
    public PropertyPersistencePort propertyPersistencePort() {
        return new PropertyPersistenceAdapter(propertyRepository, propertyEntityMapper);
    }

    @Bean
    public PropertyServicePort propertyServicePort() {
        return  new PropertyUseCase(propertyPersistencePort());
    }

}