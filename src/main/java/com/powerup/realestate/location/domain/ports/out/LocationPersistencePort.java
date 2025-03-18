package com.powerup.realestate.location.domain.ports.out;

import com.powerup.realestate.location.domain.model.LocationModel;


public interface LocationPersistencePort {
    void save(LocationModel locationModel);
    boolean existsByDepartment(String department);

}
