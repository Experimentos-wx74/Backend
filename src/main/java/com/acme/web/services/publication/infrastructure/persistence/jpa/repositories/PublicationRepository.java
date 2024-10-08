package com.acme.web.services.publication.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PublicationRepository is a JPA repository responsible for managing the Publication entities.
 * @author Ray Alessandro Del Carmen Zorrilla - U202124061
 * @version 1.0
 */
@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findAllByAdvisorId(Long advisorId);
}
