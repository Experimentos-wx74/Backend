package com.acme.web.services.core.entities.unit.tests;

import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Esta clase representa las "Core Entities Unit Tests" para el bounded context de Management.
 * Se encarga de probar la lógica de negocio de los aggregates: Cage, Animal.
 * Y las entidades: Expense y Resource.
 * @autor Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ManagementServiceTest {

    private Breeder mockBreeder;

    @BeforeEach
    void setUp() {
        mockBreeder = mock(Breeder.class);
        when(mockBreeder.getUserId()).thenReturn(1L);
    }

    @Test
    void testCreateCage() {
        // Arrange: Inicializar variables
        String cageName = "Cage 1";
        Integer cageSize = 100;
        String cageObservations = "Large cage for big animals";

        // Act: Crear la instancia de Cage
        Cage cage = new Cage(cageName, cageSize, cageObservations, mockBreeder);

        // Assert: Verificar que los valores son los esperados
        assertNotNull(cage);  // Verificamos que la jaula fue creada correctamente
        assertEquals(cageName, cage.name());  // Verificamos que el nombre se asignó correctamente
        assertEquals(cageSize, cage.size());  // Verificamos que el tamaño se asignó correctamente
        assertEquals(cageObservations, cage.observations());  // Verificamos que las observaciones se asignaron correctamente
        assertEquals(mockBreeder, cage.getBreeder());  // Verificamos que el criador se asignó correctamente
        assertTrue(cage.getAnimals().isEmpty());  // Verificamos que la lista de animales está vacía inicialmente
    }


    @Test
    void testAddAnimalToCage() {
        // Arrange: Inicializar variables
        String cageName = "Cage 1";
        Integer cageSize = 100;
        String cageObservations = "Large cage for big animals";
        Cage cage = new Cage(cageName, cageSize, cageObservations, mockBreeder);

        String animalName = "Super Cuy";
        String animalBreed = "KURI";
        Boolean animalGender = true;  // true para macho, false para hembra
        LocalDate animalBirthdate = LocalDate.of(2020, 1, 1);
        Float animalWeight = 190.0f;
        Boolean animalIsSick = false;
        String animalObservations = "Crece bien y come mucho";

        // Act: Crear el animal y añadirlo a la jaula
        Animal animal = new Animal(animalName, animalBreed, animalGender, animalBirthdate, animalWeight, animalIsSick, animalObservations, cage);
        cage.getAnimals().add(animal);

        // Assert: Verificar que el animal se ha añadido a la jaula
        assertEquals(1, cage.getAnimals().size());  // Verificamos que hay un animal en la jaula
        assertEquals(cage, animal.getCage());  // Verificamos que el animal tenga la jaula correcta asignada
    }



    @Test
    void testCreateExpense() {
        // Arrange: Inicializar variables
        String name = "Compra de alimento";
        String type = "ALIMENTO"; // Asumiendo que este es un tipo válido
        Float amountValue = 150.0f; // Monto de la expensa
        LocalDate date = LocalDate.now(); // Fecha de creación
        String observationsValue = "Compra mensual de alimento";
        CreateExpenseCommand command = new CreateExpenseCommand(name, type, amountValue, date, observationsValue, 1L);

        // Act: Crear la instancia de Expense usando el comando
        Expense expense = new Expense(command, mockBreeder);

        // Assert: Verificar que los valores son los esperados
        assertNotNull(expense);  // Verificamos que la expensa fue creada correctamente
        assertEquals(name, expense.getName());  // Verificamos que el nombre se asignó correctamente
        assertEquals(amountValue, expense.getAmount());  // Verificamos que la cantidad se asignó correctamente
        assertEquals(date, expense.getDate());  // Verificamos que la fecha se asignó correctamente
        assertEquals(observationsValue, expense.getObservations());  // Verificamos que las observaciones se asignaron correctamente
    }

    @Test
    void testCreateResource() {
        // Arrange: Inicializar variables
        String name = "Alfalfa";
        String type = "ALIMENTO";
        Integer quantityValue = 200;
        LocalDate date = LocalDate.now();
        String observationsValue = "Recursos para la alimentación del ganado";

        CreateResourceCommand command = new CreateResourceCommand(name, type, quantityValue, date, observationsValue,1L);

        // Act: Crear la instancia de Resource usando el comando
        Resource resource = new Resource(command, mockBreeder);

        // Assert: Verificar que los valores son los esperados
        assertNotNull(resource);  // Verificamos que el recurso fue creado correctamente
        assertEquals(name, resource.getName());  // Verificamos que el nombre se asignó correctamente
        assertEquals(quantityValue, resource.getQuantity());  // Verificamos que la cantidad se asignó correctamente
        assertEquals(date, resource.getDate());  // Verificamos que la fecha se asignó correctamente
    }
}
