package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.List;
import java.util.Optional;

public interface PropertyPersistencePort {
    void save(PropertyModel propertyModel);
    PageResult<PropertyModel> getProperties(
            Long sellerId,
            Integer page,
            Integer size,
            String location,
            String category,
            Integer rooms,
            Integer bathrooms,
            Double minPrice,
            Double maxPrice,
            String sortBy,
            boolean orderAsc);

    List<PropertyModel> findByPublicationStatus(PublicationStatus status);
    void update(PropertyModel property);
    Optional<PropertyModel> findById(Long id);
    List<PropertyModel> findBySellerId(Long sellerId);

}

