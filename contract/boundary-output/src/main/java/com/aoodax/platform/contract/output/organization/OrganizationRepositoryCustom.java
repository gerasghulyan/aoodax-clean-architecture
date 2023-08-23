package com.aoodax.platform.contract.output.organization;

import com.aoodax.platform.contract.model.organization.OrganizationModel;
import com.aoodax.platform.infrastructure.domain.entity.organization.OrganizationDocument;

public interface OrganizationRepositoryCustom {

    OrganizationDocument create(OrganizationModel model);

}
