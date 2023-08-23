package am.urgnam.platform.domain.model.document.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractContentAwareDomainEntity extends AbstractDocument {

    private String alias;
    private String name;
    private EntityStatus status;
    private String description;

}
