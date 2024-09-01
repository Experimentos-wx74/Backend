package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * ExpenseResource represents the resource for an expense.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */

public record ExpenseResource(Long id,
                              String name,
                              String type,
                              Float amount,
                              LocalDate date,
                              String observations,
                              Long breederId) {
}
