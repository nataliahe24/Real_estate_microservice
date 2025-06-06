package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.InvalidActivePublicationDateException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.in.PropertyServicePort;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.domain.utils.constants.PropertyDomainContants;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.domain.utils.validation.PropertyValidation;
import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertyUseCase implements PropertyServicePort {
    private final PropertyPersistencePort propertyPersistencePort;
    private final LocationPersistencePort locationPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    @Override
    public void saveProperty(PropertyModel propertyModel) throws LocationNotFoundException, CategoryNotFoundException {
        
        validateActivePublicationDate(propertyModel.getActivePublicationDate());
        
        PropertyValidation.validatePropertyLocationAndCategory(
                propertyModel,
                locationPersistencePort,
                categoryPersistencePort
        );
        
        propertyModel.setPublicationStatus(PublicationStatus.PUBLISHING_PAUSED);
        propertyModel.setPublicationDate(LocalDate.now());

        propertyPersistencePort.save(propertyModel);
    }

    private void validateActivePublicationDate(LocalDate activePublicationDate) {
        if (activePublicationDate != null && activePublicationDate.isAfter(LocalDate.now().plusMonths(1))) {
            throw new InvalidActivePublicationDateException();
        }
    }

    @Override
    public PageResult<PropertyModel> getProperties(Long sellerId, Integer page, Integer size, String location,
                                                   String category, Integer rooms, Integer bathrooms,
                                                   Double minPrice, Double maxPrice, String sortBy, boolean orderAsc) {
        return propertyPersistencePort.getProperties(
                sellerId,
                page,
                size,
                location,
                category,
                rooms,
                bathrooms,
                minPrice,
                maxPrice,
                sortBy, orderAsc);
    }


    public PropertyModel getPropertyById(Long id) {
        return propertyPersistencePort.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException(PropertyDomainContants.PROPERTY_NOT_FOUND + id));
    }
    
    public List<PropertyModel> getPropertiesBySellerId(Long sellerId) {
        return propertyPersistencePort.findBySellerId(sellerId);
    }
}
