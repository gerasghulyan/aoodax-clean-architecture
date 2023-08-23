package am.urgnam.platform.domain.model.document.place;

import am.urgnam.platform.domain.model.document.common.AbstractContentAwareDomainEntity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class Place extends AbstractContentAwareDomainEntity {

    private List<String> locationsUids;
    private List<String> categoriesUids;
    private List<String> tagsUids;
    private String logoUuid;
    private String phone;
    private String website;
}