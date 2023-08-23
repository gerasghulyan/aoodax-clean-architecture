package am.urgnam.platform.domain.model.document.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDocument implements Document {

    @Id
    private String uid;

    @Field("created_at")
    @CreatedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @Field("updated_at")
    @LastModifiedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    @Field("is_deleted")
    private boolean isDeleted;

    @Version
    @EqualsAndHashCode.Exclude
    private Long version;
}
