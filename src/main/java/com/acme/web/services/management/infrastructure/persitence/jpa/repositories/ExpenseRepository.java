package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for Expense entity.
 * It extends JpaRepository to get access to CRUD operations.
 * it contains a method to find all expenses by breeder id.
 * @author Giacomo Zoppi Rodriguez - u2022210029 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByBreederId(Long breederId);
}