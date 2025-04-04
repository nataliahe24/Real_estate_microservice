package com.powerup.realestate.location.domain.model;

import com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants;
import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class LocationModel {

    @Setter
    private  Long id;
    private CityEntity cityId;
    private  String neighborhood;

    public LocationModel(Long id, CityEntity cityId, String neighborhood) {

        this.id = id;
        this.cityId = cityId;
        this.neighborhood = neighborhood;
    }

    public void setCityId(CityEntity cityId) {
        this.cityId = Objects.requireNonNull(cityId,  LocationDomainConstants.FIELD_CITY_NULL_MESSAGE);
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = Objects.requireNonNull(neighborhood,  LocationDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}
