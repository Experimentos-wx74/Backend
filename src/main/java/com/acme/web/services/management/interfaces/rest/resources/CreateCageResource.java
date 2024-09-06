package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

/**
 * CreateCageResource represents the resource to create a cage.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */

public record CreateCageResource(
        @NotNull String name,
        @NotNull Integer size,
        @NotNull String observations,
        @NotNull Long breederId) {
}
