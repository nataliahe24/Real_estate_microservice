package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.commons.configurations.utils.Constants;
import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.infrastructure.entities.LocationEntity;
import com.powerup.realestate.properties.infrastructure.mappers.LocationEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements LocationPersistencePort {
    private final LocationRepository locationRepository;
    private final LocationEntityMapper locationEntityMapper;

    @Override
    public void save(LocationModel locationModel) {
        locationRepository.save(locationEntityMapper.modelToEntity(locationModel));
    }

    @Override
    public Optional<LocationModel> findByLocationId(Long locationId) {
        return locationRepository.findById(locationId)
                .map(locationEntityMapper::entityToModel);
    }

    @Override
    public PageResult<LocationModel> getLocations(String searchText,Integer page, Integer size, boolean orderAsc) {

        Pageable pagination;

        if (orderAsc) pagination  = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_CITY, Constants.PAGEABLE_FIELD_DEPARTMENT).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_CITY, Constants.PAGEABLE_FIELD_DEPARTMENT));

        Page<LocationEntity> pageLocations = locationRepository.findByCityOrDepartment(searchText, pagination);
        List<LocationModel> pageModel = locationEntityMapper.entityListToModelList(pageLocations.getContent());
        return new PageResult<>(pageModel, page, size, pageModel.size());
    }

    @Override
    public boolean existsByCityIdAndNeighborhoodIgnoreCase(Long cityId, String neighborhood) {
        return locationRepository.existsByCityName_IdAndNeighborhoodIgnoreCase(cityId, neighborhood);
    }

}
