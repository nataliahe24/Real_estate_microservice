package com.powerup.realestate.location.infrastructure.adapters.persistence;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.location.infrastructure.mappers.LocationEntityMapper;
import com.powerup.realestate.location.infrastructure.repositories.mysql.LocationRepository;
import com.powerup.realestate.commons.configurations.utils.Constants;
import lombok.RequiredArgsConstructor;
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

}
