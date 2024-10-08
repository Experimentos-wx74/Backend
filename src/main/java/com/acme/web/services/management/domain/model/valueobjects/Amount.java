package com.acme.web.services.management.domain.model.valueobjects;

/**
 * Amount value object represents the amount of an expense.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public record Amount(Float amount) {
    public Amount {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }
    }
}
