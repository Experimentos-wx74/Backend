package com.acme.web.services.management.domain.model.valueobjects;

/**
 * Weight value object represents the weight of an animal.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public record Weight(Float weight) {
    public Weight {
        if (weight == null || weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative or null");
        }
    }
}
