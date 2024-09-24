package com.acme.web.services.core.entities.unit.tests;
import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.commands.CreateAppointmentCommand;
import com.acme.web.services.appointment.domain.model.valueObjects.Status;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * This class represents the unit tests for the AppointmentService.
 *
 * @author Fiorella Jarama Peñaloza
 */
public class AppointmentServiceTest {
    private Breeder breeder;
    private Advisor advisor;
    private Date mockDate;
    private CreateAppointmentCommand command;

    @BeforeEach
    void setUp() {
        breeder = mock(Breeder.class);
        advisor = mock(Advisor.class);
        mockDate = new Date();
        command = new CreateAppointmentCommand(1L, 2L, mockDate, "PENDIENTE");
    }

    @Test
    void testConstructorWithCommand() {
        // Act
        Appointment appointment = new Appointment(command);
        // Assert
        assertNotNull(appointment);
        assertEquals(mockDate, appointment.getAppointmentDate());
        assertEquals(Status.PENDIENTE, appointment.getStatus());
    }

    @Test
    void testConstructorWithBreederAdvisorCommand() {
        // Act
        Appointment appointment = new Appointment(breeder, advisor, command);

        // Assert
        assertNotNull(appointment);
        assertEquals(mockDate, appointment.getAppointmentDate());
        assertEquals(Status.PENDIENTE, appointment.getStatus());
        assertEquals(breeder, appointment.getBreeder());
        assertEquals(advisor, appointment.getAdvisor());
    }

    @Test
    void testGetAppointmentDate() {
        // Arrange
        Appointment appointment = new Appointment(command);
        // Act
        Date appointmentDate = appointment.getAppointmentDate();
        // Assert
        assertEquals(mockDate, appointmentDate);
    }

    @Test
    void testSetStatus() {
        // Arrange
        Appointment appointment = new Appointment(command);
        // Act
        appointment.setStatus(Status.TERMINADO);
        // Assert
        assertEquals(Status.TERMINADO, appointment.getStatus());
    }

    @Test
    void testCreateAppointment() {
        // Arrange
        Breeder breeder = mock(Breeder.class); // Simulamos el objeto Breeder
        Advisor advisor = mock(Advisor.class); // Simulamos el objeto Advisor
        Date appointmentDate = new Date();
        String status = "PENDIENTE";
        CreateAppointmentCommand command = new CreateAppointmentCommand(1L, 2L, appointmentDate, status);

        // Act
        Appointment appointment = new Appointment(breeder, advisor, command);

        // Assert
        assertNotNull(appointment); // Verifica que la cita haya sido creada
        assertEquals(appointmentDate, appointment.getAppointmentDate()); // Verifica la fecha
        assertEquals(Status.PENDIENTE, appointment.getStatus()); // Verifica el estado inicial
        assertEquals(breeder, appointment.getBreeder()); // Verifica el breeder asignado
        assertEquals(advisor, appointment.getAdvisor()); // Verifica el advisor asignado
        assertNull(appointment.getCreatedAt()); // Verifica que las fechas de auditoría sean nulas al inicio
        assertNull(appointment.getUpdatedAt());
    }

}
