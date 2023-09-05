package com.aoodax.platform.infrastructure.repository.organization;

import com.aoodax.platform.contract.input.output.organization.OrganizationRepositoryCustom;
import com.aoodax.platform.contract.model.organization.OrganizationModel;
import com.aoodax.platform.infrastructure.domain.entity.organization.OrganizationEntity;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepositoryCustom {

    @Override
    public OrganizationEntity create(final OrganizationModel model) {
        return null;
    }
}
