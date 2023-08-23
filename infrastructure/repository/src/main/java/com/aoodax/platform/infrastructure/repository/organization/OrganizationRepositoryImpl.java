package com.aoodax.platform.infrastructure.repository.organization;

import com.aoodax.platform.contract.model.organization.OrganizationModel;
import com.aoodax.platform.contract.output.organization.OrganizationRepositoryCustom;
import com.aoodax.platform.infrastructure.domain.entity.organization.OrganizationDocument;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepositoryCustom {

    @Override
    public OrganizationDocument create(final OrganizationModel model) {
        return null;
    }
}
