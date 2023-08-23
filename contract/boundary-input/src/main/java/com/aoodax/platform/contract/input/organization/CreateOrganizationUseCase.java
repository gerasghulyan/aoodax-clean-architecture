package com.aoodax.platform.contract.input.organization;

import com.aoodax.platform.contract.input.organization.dto.request.CreateOrganizationRequestDto;
import com.aoodax.platform.contract.model.organization.OrganizationModel;

public interface CreateOrganizationUseCase {

    OrganizationModel create(CreateOrganizationRequestDto dto);

}
