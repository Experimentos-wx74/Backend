package com.acme.web.services.core.entities.unit.tests;

import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import com.acme.web.services.user.domain.model.commands.CreateBreederCommand;
import com.acme.web.services.user.domain.model.entities.AvailableDate;
import com.acme.web.services.user.domain.model.entities.Notification;
import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import com.acme.web.services.iam.domain.model.aggregates.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Esta clase representa las "Core Entities Unit Tests" para el bounded context de User.
 * Se encarga de probar la lógica de negocio de los aggregates: Advisor, Breeder.
 * Y las entidades: AvailableDate y Notification.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class UserServiceTest {

    private User mockUser;
    private Breeder mockBreeder;
    private Advisor mockAdvisor;

    @BeforeEach
    void setUp() {
        // Crear un mock para el User
        mockUser = mock(User.class);
        mockBreeder = mock(Breeder.class);
        mockAdvisor = mock(Advisor.class);
        when(mockUser.getId()).thenReturn(1L);
        when(mockBreeder.getUserId()).thenReturn(1L);
        when(mockAdvisor.getUserId()).thenReturn(1L);
    }

    @Test
    void testCreateAdvisor() {
        // Arrange: Inicializar variables
        CreateAdvisorCommand command = new CreateAdvisorCommand("Esteban Quito", "UPC Monterrico", LocalDate.of(1990, 1, 1),
                "Persona amante de los animales", "Veterinario", 5, "photo_url_firebase", 4, 1L);

        // Act: Ejecutar el metodo a testear
        Advisor advisor = new Advisor(command, mockUser);

        // Assert: Comprobación de valores esperados
        assertNotNull(advisor);
        assertEquals("Esteban Quito", advisor.getFullname());
        assertEquals("UPC Monterrico", advisor.getLocation());
        assertEquals(5, advisor.getExperience());
    }

    @Test
    void testCreateBreeder() {
        // Arrange: Inicializar variables
        CreateBreederCommand command = new CreateBreederCommand("Elca Brito", "UPC San Isidro", LocalDate.of(1985, 5, 5),
                "Estoy empezando mi granja, amo los cuyes", 1L);

        // Act: Ejecutar el metodo a testear
        Breeder breeder = new Breeder(command, mockUser);

        // Assert: Comprobación de valores esperados
        assertNotNull(breeder);
        assertEquals("Elca Brito", breeder.getFullname());
        assertEquals("UPC San Isidro", breeder.getLocation());
        assertEquals(1L, breeder.getUserId());
    }

    @Test
    void testAvailableDate() {
        // Arrange: Inicializar variables
        LocalDate date = LocalDate.of(2024, 9, 25);
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        AvailableDate availableDate = new AvailableDate(mockAdvisor, date, startTime, endTime, true);

        // Act: Ejecutar el metodo a testear
        LocalDate resultDate = availableDate.getAvailableDate();

        // Assert: Comprobación de valores esperados
        assertEquals(date, resultDate);
        assertTrue(availableDate.getStatus());
        assertEquals(startTime, availableDate.getStartTime());
        assertEquals(endTime, availableDate.getEndTime());
    }

    @Test
    void testCreateNotification() {
        // Arrange: Inicializar variables
        CreateNotificationCommand command = new CreateNotificationCommand("CITA", "Tienes una cita para el 26/09/2024 a las 16:29", new Date(), 1L,"http://meeting.url");

        // Act: Ejecutar el metodo a testear
        Notification notification = new Notification(command, mockUser);

        // Assert: Comprobación de valores esperados
        assertNotNull(notification);
        assertEquals("CITA", notification.getType());
        assertEquals("Tienes una cita para el 26/09/2024 a las 16:29", notification.getText());
        assertEquals("http://meeting.url", notification.getMeetingUrl());
    }
}
