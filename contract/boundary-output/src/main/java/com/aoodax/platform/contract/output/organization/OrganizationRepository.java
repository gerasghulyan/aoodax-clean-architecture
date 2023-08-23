package com.aoodax.platform.contract.output.organization;

import com.aoodax.platform.infrastructure.domain.entity.organization.OrganizationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<OrganizationDocument, String>, OrganizationRepositoryCustom {

}
