package com.acme.web.services.core.integration.tests;

import com.acme.web.services.appointment.domain.services.ReviewCommandService;
import com.acme.web.services.appointment.domain.services.ReviewQueryService;
import com.acme.web.services.appointment.interfaces.rest.ReviewsController;
import com.acme.web.services.appointment.interfaces.rest.resources.CreateReviewResource;
import com.acme.web.services.appointment.interfaces.rest.resources.ReviewResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReviewsControllerTest {

    private ReviewCommandService reviewsCommandService;
    private ReviewQueryService reviewsQueryService;
    private ReviewsController reviewsController;

    @BeforeEach
    void setUp() {
        reviewsCommandService = Mockito.mock(ReviewCommandService.class);
        reviewsQueryService = Mockito.mock(ReviewQueryService.class);
        reviewsController = new ReviewsController(reviewsCommandService, reviewsQueryService, null);
    }

    @Test
    void testCreateReview_Failure_BadRequest() {
        // Arrange
        Long appointmentId = 1L;
        String comment = "Great service!";
        Integer rating = 5;
        CreateReviewResource createReviewResource = new CreateReviewResource(appointmentId, comment, rating);

        when(reviewsCommandService.handle(any())).thenReturn(0L); // 0 indica que no se creó la reseña

        // Act
        ResponseEntity<ReviewResource> response = reviewsController.createReview(createReviewResource);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
    }
}
