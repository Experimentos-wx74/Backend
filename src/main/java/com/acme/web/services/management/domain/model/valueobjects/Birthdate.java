package com.acme.web.services.management.domain.model.valueobjects;

import java.time.LocalDate;

/**
 * Birthdate value object represents the birthdate of an animal.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public record Birthdate(LocalDate birthdate) {
    public Birthdate {
        if (birthdate == null) {
            throw new IllegalArgumentException("Birthdate cannot be null");
        }
    }
}
