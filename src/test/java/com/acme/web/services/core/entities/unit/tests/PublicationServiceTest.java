package com.acme.web.services.core.entities.unit.tests;
import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/**
 * This class represents the unit tests for the PublicationService.
 *
 * @author Fiorella Jarama Peñaloza
 */
public class PublicationServiceTest {
    @Test
    void testCreatePublication() {
        // Arrange
        Advisor advisor = mock(Advisor.class); // Simulamos el objeto Advisor
        Date publicationDate = new Date();
        CreatePublicationCommand command = new CreatePublicationCommand("Title", "Description", "ImageURL", publicationDate, 1L);

        // Act
        Publication publication = new Publication(command, advisor);

        // Assert
        assertNotNull(publication); // Verificamos que la publicación fue creada
        assertEquals("Title", publication.getPublicationContent().title()); // Verificamos el título
        assertEquals("Description", publication.getPublicationContent().description()); // Verificamos la descripción
        assertEquals("ImageURL", publication.getPublicationContent().image()); // Verificamos la imagen
        assertEquals(publicationDate, publication.getDate()); // Verificamos la fecha
        assertEquals(advisor, publication.getAdvisor()); // Verificamos el advisor asignado
        assertNull(publication.getCreatedAt()); // Verificamos que las fechas de auditoría sean nulas al inicio
        assertNull(publication.getUpdatedAt());
    }

    @Test
    void testUpdatePublicationContent() {
        // Arrange
        Advisor advisor = mock(Advisor.class);
        Date publicationDate = new Date();
        Publication publication = new Publication("Old Title", "Old Description", "Old Image", publicationDate, advisor);

        // Act
        publication.updatePublicationContent("New Title", "New Description", "New Image");

        // Assert
        assertEquals("New Title", publication.getPublicationContent().title()); // Verificamos el título actualizado
        assertEquals("New Description", publication.getPublicationContent().description()); // Verificamos la descripción actualizada
        assertEquals("New Image", publication.getPublicationContent().image()); // Verificamos la imagen actualizada
    }

    @Test
    void testAdvisorAssignment() {
        // Arrange
        Advisor advisor = mock(Advisor.class);
        Date publicationDate = new Date();
        Publication publication = new Publication("Title", "Description", "ImageURL", publicationDate, advisor);

        // Assert
        assertEquals(advisor, publication.getAdvisor()); // Verificamos que el advisor fue asignado correctamente
    }

    @Test
    void testPublicationDate() {
        // Arrange
        Advisor advisor = mock(Advisor.class);
        Date publicationDate = new Date();
        Publication publication = new Publication("Title", "Description", "ImageURL", publicationDate, advisor);

        // Act
        Date retrievedDate = publication.getDate();

        // Assert
        assertEquals(publicationDate, retrievedDate); // Verificamos que la fecha es la misma que fue asignada
    }

    @Test
    void testAuditFieldsAreNullInitially() {
        // Arrange
        Advisor advisor = mock(Advisor.class);
        Date publicationDate = new Date();
        Publication publication = new Publication("Title", "Description", "ImageURL", publicationDate, advisor);

        // Assert
        assertNull(publication.getCreatedAt()); // Verificamos que el campo createdAt es nulo al inicio
        assertNull(publication.getUpdatedAt()); // Verificamos que el campo updatedAt es nulo al inicio
    }


}
