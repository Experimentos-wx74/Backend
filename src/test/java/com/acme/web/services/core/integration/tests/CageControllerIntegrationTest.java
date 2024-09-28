package com.acme.web.services.core.integration.tests;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.commands.DeleteCageCommand;
import com.acme.web.services.management.domain.services.CageCommandService;
import com.acme.web.services.management.interfaces.rest.CagesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CageControllerIntegrationTest {
    @InjectMocks
    private CagesController cageController;

    @Mock
    private CageCommandService cageCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteCage() {
        // Arrange: Iniciación de valores
        Long cageId = 1L;
        Cage mockCage = new Cage();

        // Simula que handle devuelve un Optional<Cage> con mockCage.
        when(cageCommandService.handle(any(DeleteCageCommand.class))).thenReturn(Optional.of(mockCage));

        // Act: Ejecución del método a probar
        ResponseEntity<?> response = cageController.deleteCage(cageId);

        // Assert: Verificación de resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cage deleted successfully!", response.getBody());
        verify(cageCommandService, times(1)).handle(any(DeleteCageCommand.class));
    }
}
