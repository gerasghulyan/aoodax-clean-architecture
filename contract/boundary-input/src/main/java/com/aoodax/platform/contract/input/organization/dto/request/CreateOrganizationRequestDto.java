package com.aoodax.platform.contract.input.organization.dto.request;

import com.aoodax.platform.contract.input.common.ContentAwareDto;
import com.aoodax.platform.contract.input.common.CoordinatesDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;
import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CreateOrganizationRequestDto {

    ContentAwareDto contentAwareDto;
    String email;
    String phone;
    String address;
    CoordinatesDto coordinatesDto;
    String bannerUuid;
    String iconUuid;

    public CreateOrganizationRequestDto(final ContentAwareDto contentAwareDto,
                                        final String email,
                                        final String phone,
                                        final String address,
                                        final CoordinatesDto coordinatesDto,
                                        final String bannerUuid,
                                        final String iconUuid) {
        assertHasTextParameterArgument(email, "email");
        assertHasTextParameterArgument(phone, "phone");
        assertHasTextParameterArgument(address, "address");
        assertNotNullParameterArgument(coordinatesDto, "coordinatesDto");
        this.contentAwareDto = contentAwareDto;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.coordinatesDto = coordinatesDto;
        this.bannerUuid = bannerUuid;
        this.iconUuid = iconUuid;
        CoordinatesDto.builder().withLat(4D).build();
    }
}
