package com.powerup.realestate.properties.domain.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


class VisitScheduleModelTest {

    private VisitScheduleModel visitSchedule;

    private static final String FIELD_START_DATE_NULL_MESSAGE = "La fecha de inicio no puede ser nula.";
    private static final String FIELD_END_DATE_NULL_MESSAGE = "La fecha de fin no puede ser nula.";

    @BeforeEach
    void setup() {

        visitSchedule = new VisitScheduleModel(
                1L,
                1L,
                null,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                0
        );
    }

    @Test
    void shouldCreateVisitScheduleWithValidData() {
        assertNotNull(visitSchedule);
        assertEquals(0, visitSchedule.getScheduledBuyers());
        assertNotNull(visitSchedule.getStartDate());
        assertNotNull(visitSchedule.getEndDate());
    }

    @Test
    void shouldThrowExceptionWhenSetStartDateToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            visitSchedule.setStartDate(null);
        });
        assertEquals(FIELD_START_DATE_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSetEndDateToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            visitSchedule.setEndDate(null);
        });
        assertEquals(FIELD_END_DATE_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldSetStartDateCorrectly() {
        LocalDateTime newStartDate = LocalDateTime.now().plusDays(1);
        visitSchedule.setStartDate(newStartDate);
        assertEquals(newStartDate, visitSchedule.getStartDate());
    }

    @Test
    void shouldSetEndDateCorrectly() {
        LocalDateTime newEndDate = LocalDateTime.now().plusDays(2);
        visitSchedule.setEndDate(newEndDate);
        assertEquals(newEndDate, visitSchedule.getEndDate());
    }
}