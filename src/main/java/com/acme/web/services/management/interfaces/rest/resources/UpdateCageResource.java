package com.acme.web.services.management.interfaces.rest.resources;

/**
 * Represents the resource for updating a cage in the system.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */
public record UpdateCageResource(String name,
                                Integer size,
                                String observations,
                                Long breederId) {
}
