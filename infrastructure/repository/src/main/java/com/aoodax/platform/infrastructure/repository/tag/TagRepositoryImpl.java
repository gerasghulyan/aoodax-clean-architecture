package com.aoodax.platform.infrastructure.repository.tag;

import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.contract.output.tag.TagRepository;
import com.aoodax.platform.contract.output.tag.mapper.TagModelDocumentMapper;
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;
import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    MongoOperations mongoOperations;

    @Override
    public TagEntity create(final TagModel model) {
        assertNotNullParameterArgument(model, "model");

        final TagEntity entity = TagModelDocumentMapper.toDocument(model);
        log.debug("Persisting tag for entity: {}", entity);
        return mongoOperations.save(entity);
    }

    @Override
    public Optional<TagEntity> getByUid(final String uid) {
        assertHasTextParameterArgument(uid, "uid");

        final Query query = new Query().addCriteria(
                where("_id").is(uid)
                        .andOperator(where("is_deleted").is(false))
        );
        return Optional.ofNullable(mongoOperations.findOne(query, TagEntity.class));
    }

    @Override
    public Optional<TagEntity> getByName(final String name) {
        assertHasTextParameterArgument(name, "name");

        final Query query = new Query().addCriteria(
                where("name").is(name)
                        .andOperator(where("is_deleted").is(false))
        );
        return Optional.ofNullable(mongoOperations.findOne(query, TagEntity.class));
    }

    @Override
    public Optional<TagEntity> markAsRemoved(final String uid) {
        assertHasTextParameterArgument(uid, "uid");

        return getByUid(uid).map(tagEntity -> {
            tagEntity.setDeleted(true);
            return mongoOperations.save(tagEntity);
        });
    }

    @Override
    public Optional<TagEntity> update(final TagModel model) {
        assertNotNullParameterArgument(model, "model");
        
        final Update update = new Update();
        update.set("name", model.getName());
        return getByUid(model.getUid()).map(tagEntity -> {
            tagEntity.setName(model.getName());
            return mongoOperations.save(tagEntity);
        });
    }

    @Override
    public Page<TagEntity> find(final Pageable pageable) {
        assertNotNullParameterArgument(pageable, "pageable");
        
        log.debug("Executing get tags method with page info - {}", pageable);
        final Query query = new Query()
                .with(Sort.by("createdAt").descending());
        final long count = mongoOperations.count(query, TagEntity.class);
        query.with(pageable);
        final Page<TagEntity> page = PageableExecutionUtils.getPage(
                mongoOperations.find(query, TagEntity.class),
                pageable,
                () -> count
        );
        log.debug("Successfully executed get tags with page info - {}", pageable);
        return page;
    }
}
