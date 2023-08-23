package am.urgnam.platform.domain.model.document.city;

import am.urgnam.platform.domain.model.document.common.AbstractContentAwareDomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class City extends AbstractContentAwareDomainEntity {

    private String bannerUuid;
}
