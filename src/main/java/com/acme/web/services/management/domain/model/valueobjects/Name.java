package com.acme.web.services.management.domain.model.valueobjects;

/**
 * Name value object represents the name of the cage, expense, resource or an animal.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public record Name(String name) {
    public Name {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }
}
