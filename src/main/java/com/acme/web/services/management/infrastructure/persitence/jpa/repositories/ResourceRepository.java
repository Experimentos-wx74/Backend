package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.valueobjects.Name;
import com.acme.web.services.management.domain.model.valueobjects.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for Resource entity.
 * It extends JpaRepository, which contains the methods for basic CRUD operations.
 * It contains custom methods to find resources by breederId and by resource type.
 * It contains a method to verify if a resource with a specific name already exists.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findAllByBreederId(Long breederId);
    List<Resource> findAllByResourceTypeAndBreederId(ResourceType type, Long breederId);

    boolean existsByName(Name name);
}
