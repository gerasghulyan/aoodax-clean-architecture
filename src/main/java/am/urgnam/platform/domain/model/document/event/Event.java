package am.urgnam.platform.domain.model.document.event;

import am.urgnam.platform.domain.model.document.common.AbstractContentAwareDomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Event extends AbstractContentAwareDomainEntity {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String placeUid;
    private String bannerUuid;

}
