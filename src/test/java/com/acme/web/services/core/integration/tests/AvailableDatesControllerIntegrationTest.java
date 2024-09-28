package com.acme.web.services.core.integration.tests;

import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.model.commands.CreateAvailableDateCommand;
import com.acme.web.services.user.domain.model.entities.AvailableDate;
import com.acme.web.services.user.domain.model.queries.GetAllAvailableDatesQuery;
import com.acme.web.services.user.domain.model.queries.GetAvailableDateByIdQuery;
import com.acme.web.services.user.domain.services.AvailableDateCommandService;
import com.acme.web.services.user.domain.services.AvailableDateQueryService;
import com.acme.web.services.user.interfaces.rest.AvailableDatesController;
import com.acme.web.services.user.interfaces.rest.resources.AvailableDateResource;
import com.acme.web.services.user.interfaces.rest.resources.CreateAvailableDateResource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AvailableDatesControllerIntegrationTest {

    @Mock
    private AvailableDateCommandService availableDateCommandService;

    @Mock
    private AvailableDateQueryService availableDateQueryService;

    @InjectMocks
    private AvailableDatesController availableDatesController;

    @Test
    void testCreateAvailableDateSuccess() {
        // Arrange
        Long advisorId = 1L;
        LocalDate date = LocalDate.of(2024, 10, 1);
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        CreateAvailableDateResource resource = new CreateAvailableDateResource(advisorId, date, startTime, endTime, true);

        Long expectedId = 1L;

        // Simular el comportamiento del servicio
        Mockito.when(availableDateCommandService.handle(ArgumentMatchers.any(CreateAvailableDateCommand.class))).thenReturn(expectedId);
        Mockito.when(availableDateQueryService.handle(ArgumentMatchers.any(GetAvailableDateByIdQuery.class))).thenReturn(Optional.of(new AvailableDate(new Advisor(), date, startTime, endTime, true)));

        // Act
        ResponseEntity<AvailableDateResource> response = availableDatesController.createAvailableDate(resource);

        // Assert
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetAvailableDateByIdNotFound() {
        // Arrange
        Long availableDateId = 1L;
        Mockito.when(availableDateQueryService.handle(ArgumentMatchers.any(GetAvailableDateByIdQuery.class))).thenReturn(Optional.empty());

        // Act
        ResponseEntity<AvailableDateResource> response = availableDatesController.getAvailableDateById(availableDateId);

        // Assert
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllAvailableDatesSuccess() {
        // Arrange
        List<AvailableDate> availableDates = List.of(
                new AvailableDate(new Advisor(), LocalDate.of(2024, 10, 1), LocalTime.of(9, 0), LocalTime.of(17, 0), true),
                new AvailableDate(new Advisor(), LocalDate.of(2024, 10, 2), LocalTime.of(10, 0), LocalTime.of(18, 0), true)
        );

        Mockito.when(availableDateQueryService.handle(ArgumentMatchers.any(GetAllAvailableDatesQuery.class))).thenReturn(availableDates);

        // Act
        ResponseEntity<List<AvailableDateResource>> response = availableDatesController.getAllAvailableDates();

        // Assert
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(availableDates.size(), response.getBody().size());
    }
}