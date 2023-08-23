package com.aoodax.platform.infrastructure.repository.tag;

import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.contract.output.tag.TagRepository;
import com.aoodax.platform.contract.output.tag.mapper.TagModelDocumentMapper;
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;
import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Repository
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    MongoOperations mongoOperations;

    @Override
    public TagEntity create(final TagModel model) {
        assertNotNullParameterArgument(model, "TagModel");

        final TagEntity tag = TagModelDocumentMapper.toDocument(model);
        log.debug("Persisting tag for entity: {}", tag);
        return mongoOperations.save(tag);
    }

    @Override
    public Optional<TagEntity> getByUid(final String uid) {
        assertHasTextParameterArgument(uid, "uid");

        final Query query = new Query().addCriteria(
                Criteria.where("_id").is(uid)
        );
        return Optional.ofNullable(mongoOperations.findOne(query, TagEntity.class));
    }

    @Override
    public Optional<TagEntity> getByName(final String name) {
        assertHasTextParameterArgument(name, "name");

        final Query query = new Query().addCriteria(
                Criteria.where("name").is(name)
        );
        return Optional.ofNullable(mongoOperations.findOne(query, TagEntity.class));
    }


}
