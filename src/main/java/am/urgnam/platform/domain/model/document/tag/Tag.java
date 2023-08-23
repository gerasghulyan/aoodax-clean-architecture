package am.urgnam.platform.domain.model.document.tag;

import am.urgnam.platform.domain.model.document.common.AbstractDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Tag extends AbstractDocument {

    private String name;
}
