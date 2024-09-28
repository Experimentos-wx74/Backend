package com.acme.web.services.core.integration.tests;

import com.acme.web.services.appointment.domain.model.commands.CreateReviewCommand;
import com.acme.web.services.appointment.domain.model.entities.Review;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReviewControllerIntegrationTest {
    private Review review;
    private Long appointmentId;
    private String comment;
    private int rating;
    private CreateReviewCommand command;

    @BeforeEach
    void setUp() {
        appointmentId = 1L;
        comment = "Great service";
        rating = 5;
        command = new CreateReviewCommand(appointmentId, comment, rating);
        review = new Review(command);
    }

    @Test
    void testConstructorWithCommand() {
        // Act
        Review review = new Review(command);

        // Assert
        Assert.assertNotNull(review);
        Assertions.assertEquals(comment, review.getComment());
        Assertions.assertEquals(rating, review.getRating());
    }

    @Test
    void testGetComment() {
        // Act
        String reviewComment = review.getComment();

        // Assert
        Assertions.assertEquals(comment, reviewComment);
    }

    @Test
    void testGetRating() {
        // Act
        int reviewRating = review.getRating();

        // Assert
        Assertions.assertEquals(rating, reviewRating);
    }
}