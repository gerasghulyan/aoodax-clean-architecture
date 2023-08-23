package am.urgnam.platform.domain.model.document.category;

import am.urgnam.platform.domain.model.document.common.AbstractContentAwareDomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document(collection = "category")
public class Category extends AbstractContentAwareDomainEntity {

    private String iconUuid;

}
