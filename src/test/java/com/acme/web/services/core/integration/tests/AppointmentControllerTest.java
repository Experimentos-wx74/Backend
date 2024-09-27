package com.acme.web.services.core.integration.tests;

import com.acme.web.services.appointment.domain.model.commands.CreateAppointmentCommand;
import com.acme.web.services.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.acme.web.services.appointment.domain.services.AppointmentCommandService;
import com.acme.web.services.appointment.domain.services.AppointmentQueryService;
import com.acme.web.services.appointment.interfaces.rest.AppointmentsController;
import com.acme.web.services.appointment.interfaces.rest.resources.CreateAppointmentResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentsController appointmentsController;

    @Mock
    private AppointmentCommandService appointmentCommandService;

    @Mock
    private AppointmentQueryService appointmentQueryService;

    private CreateAppointmentResource createAppointmentResource;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 25);  // Año, Mes (0-based), Día
        Date appointmentDate = calendar.getTime();
        MockitoAnnotations.openMocks(this);
        createAppointmentResource = new CreateAppointmentResource( 2L, 5L, appointmentDate, "2022-12-31T23:59:59");

    }


    @Test
    void testCreateAppointment_BadRequest() {
        when(appointmentCommandService.handle(any(CreateAppointmentCommand.class))).thenReturn(0L);

        // Llamamos al método directamente
        var response = appointmentsController.createAppointment(createAppointmentResource);

        assertEquals(400, response.getStatusCodeValue());
    }


    @Test
    void testGetAppointmentById_NotFound() {
        long appointmentId = 1L;
        when(appointmentQueryService.handle(new GetAppointmentByIdQuery(appointmentId)))
                .thenReturn(Optional.empty());

        // Llamamos al método directamente
        var response = appointmentsController.getAppointmentById(appointmentId);

        assertEquals(404, response.getStatusCodeValue());
    }
}
