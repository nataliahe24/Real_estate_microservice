package com.powerup.realestate.properties.application.services;



import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.PropertyResponse;
import com.powerup.realestate.properties.application.dto.response.SavePropertyResponse;
import com.powerup.realestate.properties.domain.utils.page.PageResult;


public interface PropertyService {
    SavePropertyResponse save(SavePropertyRequest request);
    PageResult<PropertyResponse> getPropertiesByFiltersAndOrder(
            Integer page,
            Integer size,
            String location,
            String category,
            Integer rooms,
            Integer bathrooms,
            Double minPrice,
            Double maxPrice,
            String sortBy,
            boolean orderAsc


    );

    PageResult<PropertyResponse> getProperties(
            Integer page,
            Integer size,
            String location,
            String category,
            boolean orderAsc
    );
}

