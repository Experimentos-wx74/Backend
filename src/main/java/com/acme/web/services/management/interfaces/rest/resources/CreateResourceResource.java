package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * CreateExpenseResource represents the resource to create an expense.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */

public record CreateResourceResource(@NotNull String name,
                                     @NotNull String type,
                                     @NotNull Integer quantity,
                                     @NotNull LocalDate date,
                                     @NotNull String observations,
                                     @NotNull Long breederId) {
}
