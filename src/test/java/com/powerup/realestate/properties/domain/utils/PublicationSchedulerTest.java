package com.powerup.realestate.properties.domain.utils;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.scheduler.PublicationScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Collections;
import static org.mockito.Mockito.*;

class PublicationSchedulerTest {

    @Mock
    private PropertyPersistencePort propertyPersistencePort;

    @InjectMocks
    private PublicationScheduler publicationScheduler;
    private LocalDate today;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        today = LocalDate.now();
    }

    @Test
    void activatePendingPublications_noPausedProperties() {
        when(propertyPersistencePort.findByPublicationStatus(PublicationStatus.PUBLISHING_PAUSED))
                .thenReturn(Collections.emptyList());

        publicationScheduler.activatePendingPublications();

        verify(propertyPersistencePort, times(1))
                .findByPublicationStatus(PublicationStatus.PUBLISHING_PAUSED);
        verify(propertyPersistencePort, never()).update(any());
    }

}