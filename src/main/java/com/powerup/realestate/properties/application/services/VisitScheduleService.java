package com.powerup.realestate.properties.application.services;

import com.powerup.realestate.properties.application.dto.request.VisitScheduleRequest;
import com.powerup.realestate.properties.application.dto.request.SaveVisitScheduleRequest;
import com.powerup.realestate.properties.application.dto.response.SaveVisitScheduleResponse;
import com.powerup.realestate.properties.application.dto.response.VisitScheduleResponse;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.List;

public interface VisitScheduleService {
    SaveVisitScheduleResponse save(SaveVisitScheduleRequest request);
    List<VisitScheduleResponse> getSchedulesByPropertyId(Long propertyId);
    List<VisitScheduleResponse> getSchedulesBySellerId(Long sellerId);
    PageResult<VisitScheduleResponse> getSchedules(Integer page, Integer size);
    PageResult<VisitScheduleResponse> getFilteredSchedules(VisitScheduleRequest request);
} 