package am.urgnam.platform.domain.model.document.location;

import am.urgnam.platform.domain.model.document.city.City;
import am.urgnam.platform.domain.model.document.common.AbstractContentAwareDomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Location extends AbstractContentAwareDomainEntity {

    private City city;
    private String address;
    private Double lat;
    private Double lng;
    private String phone;
    private String bannerUuid;
    private int sortOrder;
}
