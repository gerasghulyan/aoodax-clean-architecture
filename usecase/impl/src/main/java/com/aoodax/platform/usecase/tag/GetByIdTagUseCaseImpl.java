package com.aoodax.platform.usecase.tag;

import com.aoodax.platform.contract.input.exception.EntityNotFoundException;
import com.aoodax.platform.contract.input.tag.GetByIdTagUseCase;
import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.contract.output.tag.TagRepository;
import com.aoodax.platform.contract.output.tag.mapper.TagModelDocumentMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;
import static java.lang.String.format;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class GetByIdTagUseCaseImpl implements GetByIdTagUseCase {

    TagRepository tagRepository;

    @Override
    public TagModel getById(final String uid) {
        assertHasTextParameterArgument(uid, "uid");

        log.debug("Retrieving tag for uid: {}", uid);
        return tagRepository.getByUid(uid)
                .map(TagModelDocumentMapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException(format("The Tag not found for uid: %s", uid)));
    }
}
