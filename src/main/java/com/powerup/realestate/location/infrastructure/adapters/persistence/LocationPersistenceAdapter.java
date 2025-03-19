package com.powerup.realestate.location.infrastructure.adapters.persistence;

import com.powerup.realestate.commons.configurations.utils.Constants;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;
import com.powerup.realestate.location.infrastructure.entities.LocationEntity;
import com.powerup.realestate.location.infrastructure.mappers.LocationEntityMapper;
import com.powerup.realestate.location.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public boolean existsByDepartment(String department) {
        return locationRepository.existsByDepartment(department);
    }

    @Override
    public PageResult<LocationModel> getLocations(String searchText,Integer page, Integer size, boolean orderAsc) {

        Pageable pagination;

        if (orderAsc) pagination  = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_CITY, Constants.PAGEABLE_FIELD_DEPARTMENT).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_CITY, Constants.PAGEABLE_FIELD_DEPARTMENT));

        Page<LocationEntity> pageLocations = locationRepository.findByCityOrDepartment(searchText, pagination);
        List<LocationModel> pageModel = locationEntityMapper.entityListToModleList(pageLocations.getContent());
        return new PageResult<>(pageModel, page, size, pageModel.size());
    }

}
