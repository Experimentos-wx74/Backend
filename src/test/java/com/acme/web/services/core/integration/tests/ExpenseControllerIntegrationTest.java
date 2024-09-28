package com.acme.web.services.core.integration.tests;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.commands.DeleteExpenseCommand;
import com.acme.web.services.management.domain.services.ExpenseCommandService;
import com.acme.web.services.management.interfaces.rest.ExpensesController;
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

/**
 * Core integration tests for the ExpenseController class.
 * Se utiliza mockito para simular el comportamiento de los servicios y verificar que el controlador se comporta como se espera
 * En este caso, se prueba el método deleteExpense
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ExpenseControllerIntegrationTest {
    @InjectMocks
    private ExpensesController expenseController;

    @Mock
    private ExpenseCommandService expenseCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteExpense() {
        // Arrange: Iniciación de valores
        Long resourceId = 1L;
        Expense mockResource = new Expense();

        // Simula que handle devuelve un Optional<Resource> con mockResource.
        when(expenseCommandService.handle(any(DeleteExpenseCommand.class))).thenReturn(Optional.of(mockResource));

        // Act: Ejecución del método a probar
        ResponseEntity<?> response = expenseController.deleteExpense(resourceId);

        // Assert: Verificación de resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Expense with given id successfully deleted", response.getBody());
        verify(expenseCommandService, times(1)).handle(any(DeleteExpenseCommand.class));
    }
}
