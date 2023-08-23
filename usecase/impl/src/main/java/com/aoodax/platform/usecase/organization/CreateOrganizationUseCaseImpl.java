package com.aoodax.platform.usecase.organization;

import com.aoodax.platform.contract.input.organization.CreateOrganizationUseCase;
import com.aoodax.platform.contract.input.organization.dto.request.CreateOrganizationRequestDto;
import com.aoodax.platform.contract.model.organization.OrganizationModel;
import org.springframework.stereotype.Service;

@Service
public class CreateOrganizationUseCaseImpl implements CreateOrganizationUseCase {

//    OrganizationRepository organizationRepository;

    @Override
    public OrganizationModel create(final CreateOrganizationRequestDto dto) {
//        final OrganizationDocument organizationDocument = organizationRepository.create(new OrganizationModel());
//        TODO handle
        return new OrganizationModel();
    }
}
