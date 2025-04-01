package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import com.powerup.realestate.properties.infrastructure.mappers.PropertyEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyPersistenceAdapter implements PropertyPersistencePort {
    private final PropertyRepository propertyRepository;
    private final PropertyEntityMapper propertyEntityMapper;


    @Override
    public void save(PropertyModel propertyModel) {
        propertyRepository.save(propertyEntityMapper.modelToEntity(propertyModel));

    }
}
