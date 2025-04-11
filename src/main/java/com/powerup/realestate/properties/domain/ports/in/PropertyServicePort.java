package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;


public interface PropertyServicePort {
    void saveProperty(PropertyModel propertyModel);
    PageResult<PropertyModel> getProperties(Integer page, Integer size,
                                            String location, String category,
                                            Integer rooms, Integer bathrooms,
                                            Double minPrice, Double maxPrice,
                                            String sortBy, boolean orderAsc);

}

