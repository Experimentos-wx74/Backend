package com.acme.web.services.core.integration.tests;
import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.commands.DeletePublicationCommand;
import com.acme.web.services.publication.domain.model.queries.GetPublicationByIdQuery;
import com.acme.web.services.publication.domain.services.PublicationCommandService;
import com.acme.web.services.publication.domain.services.PublicationQueryService;
import com.acme.web.services.publication.interfaces.rest.PublicationsController;
import com.acme.web.services.publication.interfaces.rest.resources.PublicationResource;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * Test class for PublicationController
 *
 * @author Fiorella Jarama Peñaloza
 */
public class PublicationControllerTest {
    @InjectMocks
    private PublicationsController publicationsController;

    @Mock
    private PublicationCommandService publicationCommandService;

    @Mock
    private PublicationQueryService publicationQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeletePublication() {
        Long publicationId = 1L;

        ResponseEntity<?> response = publicationsController.deletePublication(publicationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Publication with given id successfully deleted", response.getBody());
        verify(publicationCommandService, times(1)).handle(any(DeletePublicationCommand.class));
    }

}
