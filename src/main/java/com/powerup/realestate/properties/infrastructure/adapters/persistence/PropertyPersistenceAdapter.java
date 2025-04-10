package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import com.powerup.realestate.properties.infrastructure.mappers.PropertyEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<PropertyModel> findByPublicationStatus(PublicationStatus status) {
        List<PropertyEntity> entities = propertyRepository.findAllByPublicationStatus(status);
        return entities.stream()
                .map(propertyEntityMapper::entityToModel)
                .toList();
    }

    @Override
    public void update(PropertyModel property) {
        PropertyEntity entity = propertyEntityMapper.modelToEntity(property);
        propertyRepository.save(entity);
    }
}
