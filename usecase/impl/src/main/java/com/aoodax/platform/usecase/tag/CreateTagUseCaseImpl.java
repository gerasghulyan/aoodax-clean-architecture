package com.aoodax.platform.usecase.tag;

import com.aoodax.platform.contract.input.exception.AlreadyExistsException;
import com.aoodax.platform.contract.input.output.tag.TagRepository;
import com.aoodax.platform.contract.input.tag.CreateTagUseCase;
import com.aoodax.platform.contract.input.tag.dto.CreateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;
import static java.lang.String.format;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CreateTagUseCaseImpl implements CreateTagUseCase {

    TagRepository tagRepository;

    @Override
    @Transactional
    public TagModel create(final CreateTagDto dto) {
        assertNotNullParameterArgument(dto, "dto");
        validateDto(dto);

        log.debug("Creating tag for dto: {}", dto);
        final TagModel createTag = TagModel.builderForCreation().name(dto.name()).build();
        return tagRepository.create(createTag);
    }

    private void validateDto(final CreateTagDto dto) {
        log.debug("Validating tag for dto: {}", dto);
        tagRepository.getByName(dto.name()).ifPresent(tagEntity -> {
            throw new AlreadyExistsException(format("The Tag already exists for name: %s", dto.name()));
        });
    }
}
