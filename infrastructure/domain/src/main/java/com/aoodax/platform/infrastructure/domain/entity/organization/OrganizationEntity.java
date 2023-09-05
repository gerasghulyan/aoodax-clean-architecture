package com.aoodax.platform.infrastructure.domain.entity.organization;

import com.aoodax.jvm.common.persistence.mongo.document.AbstractDocument;
import com.aoodax.platform.infrastructure.domain.entity.organization.common.Address;
import com.aoodax.platform.infrastructure.domain.entity.organization.common.Contact;
import com.aoodax.platform.infrastructure.domain.entity.organization.common.Content;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class OrganizationEntity extends AbstractDocument {

    Content content;
    Contact contact;
    Address address;
    String bannerUuid;
    String iconUuid;

    @Builder
    public OrganizationEntity(final Content content,
                              final Contact contact,
                              final Address address,
                              final String bannerUuid,
                              final String iconUuid) {
        this.content = content;
        this.contact = contact;
        this.address = address;
        this.bannerUuid = bannerUuid;
        this.iconUuid = iconUuid;
    }

}
