package com.acme.web.services.core.integration.tests;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.commands.CreateAnimalCommand;
import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.domain.model.queries.GetAnimalByIdQuery;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.AnimalCommandService;
import com.acme.web.services.management.domain.services.AnimalQueryService;
import com.acme.web.services.management.interfaces.rest.AnimalController;
import com.acme.web.services.management.interfaces.rest.resources.AnimalResource;
import com.acme.web.services.management.interfaces.rest.resources.CreateAnimalResource;
import com.acme.web.services.management.interfaces.rest.resources.UpdateAnimalResource;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public class AnimalControllerIntegrationTest {

    private AnimalCommandService animalCommandService;
    private AnimalQueryService animalQueryService;
    private AnimalController animalController;

    @BeforeEach
    void setUp() {
        // Mock services
        animalCommandService = Mockito.mock(AnimalCommandService.class);
        animalQueryService = Mockito.mock(AnimalQueryService.class);

        // Instantiate the controller with mocks
        animalController = new AnimalController(animalCommandService, animalQueryService);
    }

    @Test
    void testCreateAnimalSuccess() {
        // Arrange
        CreateAnimalResource resource = new CreateAnimalResource(
                "Lion",
                "LION",
                true,
                LocalDate.now(),
                250.0f,
                false,
                "Healthy",
                1L
        );
        // Simulamos la creación de una jaula con un breeder ficticio
        Breeder breeder = Mockito.mock(Breeder.class); // Mock del breeder
        Cage cage = new Cage("Savanna", 20, "Large cage for lions", breeder);  // Creamos una instancia válida de Cage

        // Creamos un animal con esa jaula
        Animal animal = new Animal(
                new Name("Lion"),
                Breed.ANDINA,
                new Gender(true),
                new Birthdate(LocalDate.now()),
                new Weight(250.0f),
                new IsSick(false),
                new Observations("Healthy"),
                cage
        );

        // Simulamos el comportamiento de los servicios
        Mockito.when(animalCommandService.handle(ArgumentMatchers.any(CreateAnimalCommand.class))).thenReturn(1L);
        Mockito.when(animalQueryService.handle(ArgumentMatchers.any(GetAnimalByIdQuery.class))).thenReturn(Optional.of(animal));

        // Act
        ResponseEntity<AnimalResource> response = animalController.createAnimal(resource);

        // Assert
        Assert.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetAnimalByIdSuccess() {
        // Arrange
        Long animalId = 1L;

        // Simulamos la creación de una jaula y un breeder
        Breeder breeder = Mockito.mock(Breeder.class);
        Cage cage = new Cage("Savanna", 20, "Large cage for lions", breeder);  // Instancia de Cage
        Animal animal = new Animal(
                new Name("Lion"),
                Breed.ANDINA, // Asegúrate de que Breed.LION esté definido
                new Gender(true),
                new Birthdate(LocalDate.now()),
                new Weight(250.0f),
                new IsSick(false),
                new Observations("Healthy"),
                cage
        );

        Mockito.when(animalQueryService.handle(ArgumentMatchers.any(GetAnimalByIdQuery.class))).thenReturn(Optional.of(animal));

        // Act
        ResponseEntity<AnimalResource> response = animalController.getAnimalById(animalId);

        // Assert
        Assert.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
