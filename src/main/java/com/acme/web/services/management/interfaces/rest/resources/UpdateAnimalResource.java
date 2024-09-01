package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * UpdateAnimalResource represents the data to update an animal.
 * @author Giacomo Zoppi Rodriguez - u202210029
 * @version 1.0
 */

public record UpdateAnimalResource(
        String name,
        String breed,
        Boolean gender,
        LocalDate birthdate,
        Float weight,
        Boolean isSick,
        String observations,
        Long cageId) {
}
