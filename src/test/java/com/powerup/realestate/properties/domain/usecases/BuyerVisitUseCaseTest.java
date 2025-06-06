package com.powerup.realestate.properties.domain.usecases;
import com.powerup.realestate.properties.domain.exceptions.*;
import com.powerup.realestate.properties.domain.model.*;
import com.powerup.realestate.properties.domain.ports.out.BuyerVisitPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuyerVisitUseCaseTest {

    @Mock
    private BuyerVisitPersistencePort buyerVisitPersistencePort;

    @Mock
    private VisitSchedulePersistencePort visitSchedulePersistencePort;

    @InjectMocks
    private BuyerVisitUseCase buyerVisitUseCase;

    private BuyerVisitModel buyerVisit;
    private VisitScheduleModel visitSchedule;
    private PropertyModel property;
    private CategoryModel category;
    private LocationModel location;
    private CityEntity city;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        city = Mockito.mock(CityEntity.class);
        when(city.getId()).thenReturn(1L);
        when(city.getName()).thenReturn("TestCity");

        category = new CategoryModel(1L, "Casa", "Casa familiar");

        location = new LocationModel(1L, city, "Centro");

        property = new PropertyModel(
                1L,
                "Test Property",
                "Description",
                "Address",
                category,
                3,
                2,
                new BigDecimal("250000"),
                location,
                LocalDate.now(),
                PublicationStatus.PUBLISHED,
                LocalDate.now(),
                1L
        );


        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusHours(1);

        visitSchedule = new VisitScheduleModel(
                1L,
                1L,
                property,
                startDate,
                endDate,
                0
        );


        buyerVisit = new BuyerVisitModel(
                1L,
                visitSchedule,
                "test@example.com"
        );


        when(visitSchedulePersistencePort.existsById(1L)).thenReturn(true);
        when(visitSchedulePersistencePort.findById(1L)).thenReturn(Optional.of(visitSchedule));
        when(buyerVisitPersistencePort.findByBuyerEmailAndVisitScheduleId(anyString(), anyLong()))
                .thenReturn(Optional.empty());
        when(buyerVisitPersistencePort.countByVisitScheduleId(1L)).thenReturn(1);
    }

    @Test
    void scheduleBuyerVisit_Success() {
        assertDoesNotThrow(() -> buyerVisitUseCase.scheduleBuyerVisit(buyerVisit));

        verify(buyerVisitPersistencePort).save(buyerVisit);
        verify(visitSchedulePersistencePort).updateScheduledBuyersCount(1L, 1);
    }

    @Test
    void scheduleBuyerVisit_InvalidEmail_ThrowsException() {
        buyerVisit.setBuyerEmail("invalid-email");

        assertThrows(InvalidEmailFormatException.class,
                () -> buyerVisitUseCase.scheduleBuyerVisit(buyerVisit));
    }

    @Test
    void scheduleBuyerVisit_ScheduleNotFound_ThrowsException() {
        when(visitSchedulePersistencePort.existsById(1L)).thenReturn(false);

        assertThrows(ScheduleNotFountExceptions.class,
                () -> buyerVisitUseCase.scheduleBuyerVisit(buyerVisit));
    }

    @Test
    void scheduleBuyerVisit_PastSchedule_ThrowsException() {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        visitSchedule = new VisitScheduleModel(
                1L,
                1L,
                property,
                pastDate,
                pastDate.plusHours(1),
                0
        );
        buyerVisit.setVisitSchedule(visitSchedule);

        when(visitSchedulePersistencePort.existsById(1L)).thenReturn(true);
        when(visitSchedulePersistencePort.findById(1L)).thenReturn(Optional.of(visitSchedule));

        assertThrows(InvalidVisitScheduleException.class,
                () -> buyerVisitUseCase.scheduleBuyerVisit(buyerVisit));
    }

    @Test
    void scheduleBuyerVisit_MaxVisitorsExceeded_ThrowsException() {
        when(buyerVisitPersistencePort.countByVisitScheduleId(1L)).thenReturn(2);

        assertThrows(MaxVisitException.class,
                () -> buyerVisitUseCase.scheduleBuyerVisit(buyerVisit));
    }
} 