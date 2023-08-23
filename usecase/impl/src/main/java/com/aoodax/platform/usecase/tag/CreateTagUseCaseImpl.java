package com.aoodax.platform.usecase.tag;

import com.aoodax.platform.contract.input.exception.EntityAlreadyExistsException;
import com.aoodax.platform.contract.input.tag.CreateTagUseCase;
import com.aoodax.platform.contract.input.tag.dto.CreateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.contract.output.tag.TagRepository;
import com.aoodax.platform.contract.output.tag.mapper.TagModelDocumentMapper;
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;
import static java.lang.String.format;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CreateTagUseCaseImpl implements CreateTagUseCase {

    TagRepository tagRepository;

    @Override
    public TagModel create(final CreateTagDto dto) {
        assertNotNullParameterArgument(dto, "CreateTagRequestDto");

        validateDto(dto);
        log.debug("Creating tag for dto: {}", dto);
        final TagModel createTag = TagModel.builderForCreation().name(dto.getName()).build();
        final TagEntity tag = tagRepository.create(createTag);
        log.debug("Successfully created tag with entity: {}", tag);
        return TagModelDocumentMapper.toModel(tag);
    }

    private void validateDto(final CreateTagDto dto) {
        log.debug("Validating tag for dto: {}", dto);
        tagRepository.getByName(dto.getName()).ifPresent(tagEntity -> {
            throw new EntityAlreadyExistsException(format("The Tag already exists for name: %s", dto.getName()));
        });
    }
}
