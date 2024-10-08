package com.acme.web.services.management.interfaces.rest.resources;

/**
 * Resource represents the resource of the application.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */
public record ResourceResource(Long id,
                               String name,
                               String type,
                               Integer quantity,
                               java.time.LocalDate date,
                               String observations,
                               Long breederId) {
}
