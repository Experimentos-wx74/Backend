package com.acme.web.services.publication.application.internal.queryservices;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.queries.GetPublicationsByAdvisorIdQuery;
import com.acme.web.services.publication.domain.model.queries.GetAllPublicationsQuery;
import com.acme.web.services.publication.domain.model.queries.GetPublicationByIdQuery;
import com.acme.web.services.publication.domain.services.PublicationQueryService;
import com.acme.web.services.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the service implementation for the PublicationQueryService.
 * It handles the queries related to the Publication entity.
 * @author Ray Alessandro Del Carmen Zorrilla - U202124061
 * @version 1.0
 */

@Service
public class PublicationQueryServiceImpl implements PublicationQueryService {
    private final PublicationRepository publicationRepository;

    public PublicationQueryServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> handle(GetAllPublicationsQuery query) {
        return publicationRepository.findAll();
    }

    @Override
    public Optional<Publication> handle(GetPublicationByIdQuery query) {
        return publicationRepository.findById(query.publicationId());
    }

    @Override
    public List<Publication> handle(GetPublicationsByAdvisorIdQuery query) {
        return publicationRepository.findAllByAdvisorId(query.advisorId());
    }

}
