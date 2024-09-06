package com.acme.web.services.management.domain.model.valueobjects;

import java.time.LocalDate;

/**
 * DateOfCreation value object represents the date of creation of an expense or a resource.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public record DateOfCreation(LocalDate date) {
    public DateOfCreation {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }
}
