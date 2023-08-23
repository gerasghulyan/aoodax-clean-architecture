package com.aoodax.platform.infrastructure.domain.entity.organization.tag;

import com.aoodax.jvm.common.persistence.mongo.document.AbstractDocument;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document("tag")
public class TagEntity extends AbstractDocument {

    private String name;

    @PersistenceCreator
    @Builder
    public TagEntity(final String uid,
                     final LocalDateTime createdAt,
                     final LocalDateTime updatedAt,
                     final boolean isDeleted,
                     final Long version,
                     final String name) {
        super(uid, createdAt, updatedAt, isDeleted, version);
        this.name = name;
    }
}
