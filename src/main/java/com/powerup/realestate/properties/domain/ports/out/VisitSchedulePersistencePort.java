package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.List;

public interface VisitSchedulePersistencePort {
    void save(VisitScheduleModel visitScheduleModel);
    List<VisitScheduleModel> findByPropertyId(Long propertyId);
    List<VisitScheduleModel> findBySellerId(Long sellerId);
    PageResult<VisitScheduleModel> getSchedules(Integer page, Integer size);
} 