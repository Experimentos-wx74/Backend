package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * AnimalResource represents the resource for an animal.
 * It is used to expose the animal information through the REST API.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */

public record AnimalResource(
        Long id,
        String name,
        String breed,
        Boolean gender,
        LocalDate birthdate,
        Float weight,
        Boolean isSick,
        String observations,
        Long cageId) {
}
