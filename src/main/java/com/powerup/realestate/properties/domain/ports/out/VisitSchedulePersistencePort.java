package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitSchedulePersistencePort {
    void save(VisitScheduleModel visitSchedule);

    Optional<VisitScheduleModel> findById(Long id);

    boolean existsById(Long id);

    List<VisitScheduleModel> findByPropertyId(Long propertyId);

    List<VisitScheduleModel> findBySellerId(Long sellerId);

    PageResult<VisitScheduleModel> findAll(Integer page, Integer size);

    PageResult<VisitScheduleModel> findByFilter(LocalDateTime startDate, LocalDateTime endDate,
                                                String location, Integer page, Integer size);

    void updateScheduledBuyersCount(Long scheduleId, int count);

    PageResult<VisitScheduleModel> getSchedules(Integer page, Integer size);

    PageResult<VisitScheduleModel> getFilteredSchedules(LocalDateTime startDate,
                                                        LocalDateTime endDate,
                                                        String location,
                                                        Integer page,
                                                        Integer size);

    boolean existsByPropertyAndScheduleOverlap(Long id, LocalDateTime startDate, LocalDateTime endDate);
}