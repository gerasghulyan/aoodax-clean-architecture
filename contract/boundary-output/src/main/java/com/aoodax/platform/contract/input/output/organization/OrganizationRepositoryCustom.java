package com.aoodax.platform.contract.input.output.organization;

import com.aoodax.platform.contract.model.organization.OrganizationModel;
import com.aoodax.platform.infrastructure.domain.entity.organization.OrganizationEntity;

public interface OrganizationRepositoryCustom {

    OrganizationEntity create(OrganizationModel model);

}
