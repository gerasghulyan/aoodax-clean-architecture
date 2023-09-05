package com.aoodax.platform.contract.input.output.organization;

import com.aoodax.platform.infrastructure.domain.entity.organization.OrganizationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<OrganizationEntity, String>, OrganizationRepositoryCustom {

}
