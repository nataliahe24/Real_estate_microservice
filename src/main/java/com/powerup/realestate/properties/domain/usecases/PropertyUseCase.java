package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.domain.utils.validation.PropertyValidation;
import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertyUseCase implements PropertyServicePort {
    private final PropertyPersistencePort propertyPersistencePort;
    private final LocationPersistencePort locationPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    @Override
    public void saveProperty(PropertyModel propertyModel) throws LocationNotFoundException, CategoryNotFoundException {
        PropertyValidation.validatePropertyLocationAndCategory(
                propertyModel,
                locationPersistencePort,
                categoryPersistencePort
        );
        propertyModel.setPublicationStatus(PublicationStatus.PUBLISHING_PAUSED);

        propertyPersistencePort.save(propertyModel);
    }

    @Override
    public PageResult<PropertyModel> getProperties(Integer page, Integer size, String location,
                                                   String category, Integer rooms, Integer bathrooms,
                                                   Double minPrice, Double maxPrice, String sortBy, boolean orderAsc) {
        return propertyPersistencePort.getProperties(
                page,
                size,
                location,
                category,
                rooms,
                bathrooms,
                minPrice,
                maxPrice,
                sortBy,
                orderAsc);
    }

    public PropertyModel getPropertyById(Long id) {
        return propertyPersistencePort.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Propiedad no encontrada con ID: " + id));
    }
    
    public List<PropertyModel> getPropertiesBySellerId(Long sellerId) {
        return propertyPersistencePort.findBySellerId(sellerId);
    }
}
