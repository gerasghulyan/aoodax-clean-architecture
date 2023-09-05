package com.aoodax.platform.usecase.tag;

import com.aoodax.platform.contract.input.exception.NotFoundException;
import com.aoodax.platform.contract.input.output.tag.TagRepository;
import com.aoodax.platform.contract.input.tag.DeleteTagUseCase;
import com.aoodax.platform.contract.model.tag.TagModel;
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
public class DeleteTagUseCaseImpl implements DeleteTagUseCase {

    TagRepository tagRepository;

    @Override
    public TagModel delete(final String uid) {
        assertHasTextParameterArgument(uid, "uid");

        log.debug("Deleting tag for uid: {}", uid);
        return tagRepository.markAsRemoved(uid)
                .orElseThrow(() -> new NotFoundException(format("The Tag not found for uid: %s", uid)));
    }
}
