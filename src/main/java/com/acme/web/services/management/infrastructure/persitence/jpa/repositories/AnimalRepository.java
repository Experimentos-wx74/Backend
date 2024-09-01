package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for the Animal entity.
 * It extends JpaRepository to have access to CRUD operations.
 * It contains a method to find all animals by cage id.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByCageId(Long cageId);
}
