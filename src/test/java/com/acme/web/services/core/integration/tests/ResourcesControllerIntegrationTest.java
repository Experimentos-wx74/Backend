package com.acme.web.services.core.integration.tests;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesQuery;
import com.acme.web.services.management.domain.model.queries.GetResourceByIdQuery;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.ResourceCommandService;
import com.acme.web.services.management.domain.services.ResourceQueryService;
import com.acme.web.services.management.interfaces.rest.ResourcesController;
import com.acme.web.services.management.interfaces.rest.resources.CreateResourceResource;
import com.acme.web.services.management.interfaces.rest.resources.ResourceResource;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ResourcesControllerIntegrationTest {

    private ResourceCommandService resourceCommandService;
    private ResourceQueryService resourceQueryService;
    private ResourcesController resourcesController;

    @BeforeEach
    void setUp() {
        resourceCommandService = Mockito.mock(ResourceCommandService.class);
        resourceQueryService = Mockito.mock(ResourceQueryService.class);
        resourcesController = new ResourcesController(resourceCommandService, resourceQueryService);
    }

    @Test
    void testCreateResourceSuccess() {
        CreateResourceResource resourceRequest = new CreateResourceResource(
                "Resource Name",
                "Resource Type",
                10,
                LocalDate.now(),
                "Some observations",
                1L
        );
        Long resourceId = 1L;
        Mockito.when(resourceCommandService.handle(ArgumentMatchers.any(CreateResourceCommand.class))).thenReturn(resourceId);
        Mockito.when(resourceQueryService.handle(ArgumentMatchers.any(GetResourceByIdQuery.class))).thenReturn(Optional.of(new Resource(
                new Name("Sample Resource Name"),
                ResourceType.CULTIVO,
                new Quantity(10),
                new DateOfCreation(LocalDate.now()),
                new Observations("Some observations here"),
                new Breeder()
        )));
        ResponseEntity<ResourceResource> response = resourcesController.createResource(resourceRequest);
        Assert.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testCreateResourceBadRequest() {
        // Arrange
        CreateResourceResource resourceRequest = new CreateResourceResource(
                "Resource Name",
                "Resource Type",
                10,
                LocalDate.now(),
                "Some observations",
                1L
        );
        Mockito.when(resourceCommandService.handle(ArgumentMatchers.any(CreateResourceCommand.class))).thenReturn(0L);
        // Act
        ResponseEntity<ResourceResource> response = resourcesController.createResource(resourceRequest);
        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAllResourcesSuccess() {
        // Arrange
        Mockito.when(resourceQueryService.handle(ArgumentMatchers.any(GetAllResourcesQuery.class)))
                .thenReturn(Collections.singletonList(new Resource(
                        new Name("Sample Resource Name"),
                        ResourceType.OTROS, // Asegúrate de usar un valor válido
                        new Quantity(10), // Valor de cantidad de ejemplo
                        new DateOfCreation(LocalDate.now()), // Fecha actual
                        new Observations("Some observations here"),
                        new Breeder()
                )));

        // Act
        ResponseEntity<List<ResourceResource>> response = resourcesController.getAllResources();

        // Assert
        Assert.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testGetResourceByIdSuccess() {
        // Arrange
        Long resourceId = 1L;
        Mockito.when(resourceQueryService.handle(ArgumentMatchers.any(GetResourceByIdQuery.class)))
                .thenReturn(Optional.of(new Resource(
                        new Name("Sample Resource Name"),
                        ResourceType.CULTIVO, // Asegúrate de usar un valor válido
                        new Quantity(10), // Valor de cantidad de ejemplo
                        new DateOfCreation(LocalDate.now()), // Fecha actual
                        new Observations("Some observations here"),
                        new Breeder(/* inicializa con los datos necesarios del criador, o usa un mock si es necesario */)
                )));

        // Act
        ResponseEntity<ResourceResource> response = resourcesController.getResourceById(resourceId);

        // Assert
        Assert.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
