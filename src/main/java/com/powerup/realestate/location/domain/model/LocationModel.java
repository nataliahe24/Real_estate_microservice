package com.powerup.realestate.location.domain.model;

import com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants;
import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Builder
@Getter
public class LocationModel {

    @Setter
    private  Long id;
    private CityEntity cityName;
    private  String neighborhood;

    public LocationModel(Long id, CityEntity cityName, String neighborhood) {


        this.id = id;
        this.cityName = cityName;
        this.neighborhood = neighborhood;
    }

    public void setCityName(CityEntity cityName) {
        this.cityName = Objects.requireNonNull(cityName,  LocationDomainConstants.FIELD_CITY_NULL_MESSAGE);
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = Objects.requireNonNull(neighborhood,  LocationDomainConstants.FIELD_NEIGHBORHOOD_NULL_MESSAGE);
    }
}
