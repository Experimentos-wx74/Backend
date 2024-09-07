package com.acme.web.services.management.domain.model.valueobjects;

/**
 * Observations value object represents the observations of a cage, expense, resource or an animal.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public record Observations(String observations) {
    public Observations {
        if (observations == null) {
            throw new IllegalArgumentException("Observations cannot be null");
        }
    }
}

