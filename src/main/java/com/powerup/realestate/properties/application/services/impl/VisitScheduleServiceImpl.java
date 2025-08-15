package com.powerup.realestate.properties.application.services.impl;

import com.powerup.realestate.properties.application.dto.request.VisitScheduleRequest;
import com.powerup.realestate.properties.application.dto.request.SaveVisitScheduleRequest;
import com.powerup.realestate.properties.application.dto.response.SaveVisitScheduleResponse;
import com.powerup.realestate.properties.application.dto.response.VisitScheduleResponse;
import com.powerup.realestate.properties.application.services.VisitScheduleService;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.usecases.PropertyUseCase;
import com.powerup.realestate.properties.domain.usecases.VisitScheduleUseCase;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.powerup.realestate.properties.domain.utils.constants.VisitScheduleDomainConstants.SAVE_VISIT_SCHEDULE_RESPONSE_MESSAGE;

@Service
@RequiredArgsConstructor
public class VisitScheduleServiceImpl implements VisitScheduleService {
    private final VisitScheduleUseCase visitScheduleUseCase;
    private final PropertyUseCase propertyUseCase;
    
    @Override
    public SaveVisitScheduleResponse save(SaveVisitScheduleRequest request) {
        PropertyModel property = propertyUseCase.getPropertyById(request.propertyId());
        
        VisitScheduleModel visitSchedule = VisitScheduleModel.builder()
                .sellerId(request.sellerId())
                .property(property)
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
        
        visitScheduleUseCase.createSchedule(visitSchedule);
        
        return new SaveVisitScheduleResponse(
                SAVE_VISIT_SCHEDULE_RESPONSE_MESSAGE,
                LocalDateTime.now()
        );
    }
    
    @Override
    public List<VisitScheduleResponse> getSchedulesByPropertyId(Long propertyId) {
        List<VisitScheduleModel> schedules = visitScheduleUseCase.getSchedulesByPropertyId(propertyId);
        return schedules.stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    @Override
    public List<VisitScheduleResponse> getSchedulesBySellerId(Long sellerId) {
        List<VisitScheduleModel> schedules = visitScheduleUseCase.getSchedulesBySellerId(sellerId);
        return schedules.stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    @Override
    public PageResult<VisitScheduleResponse> getSchedules(Integer page, Integer size) {
        PageResult<VisitScheduleModel> pageResult = visitScheduleUseCase.getSchedules(page, size);
        
        List<VisitScheduleResponse> responseList = pageResult.getContent().stream()
                .map(this::mapToResponse)
                .toList();
        
        return new PageResult<>(
                responseList,
                pageResult.getPage(),
                pageResult.getSize(),
                pageResult.getTotalElements()
        );
    }
    
    @Override
    public PageResult<VisitScheduleResponse> getFilteredSchedules(VisitScheduleRequest request) {
        PageResult<VisitScheduleModel> pageResult = visitScheduleUseCase.getFilteredSchedules(
                request.startDate(),
                request.endDate(),
                request.location(),
                request.page(),
                request.size()
        );
        
        List<VisitScheduleResponse> responseList = pageResult.getContent().stream()
                .map(this::mapToResponse)
                .toList();
        
        return new PageResult<>(
                responseList,
                pageResult.getPage(),
                pageResult.getSize(),
                pageResult.getTotalElements()
        );
    }
    
    private VisitScheduleResponse mapToResponse(VisitScheduleModel model) {
        return new VisitScheduleResponse(
                model.getId(),
                model.getSellerId(),
                model.getProperty().getId(),
                model.getProperty().getName(),
                model.getProperty().getLocation().getNeighborhood(),
                model.getProperty().getLocation().getCityName().getName(),
                model.getProperty().getAddress(),
                model.getStartDate(),
                model.getEndDate()
        );
    }
} 