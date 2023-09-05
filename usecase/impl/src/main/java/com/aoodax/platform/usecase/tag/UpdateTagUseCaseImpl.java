package com.aoodax.platform.usecase.tag;

import com.aoodax.platform.contract.input.exception.AlreadyExistsException;
import com.aoodax.platform.contract.input.exception.DbException;
import com.aoodax.platform.contract.input.exception.NotFoundException;
import com.aoodax.platform.contract.input.output.tag.TagRepository;
import com.aoodax.platform.contract.input.tag.UpdateTagUseCase;
import com.aoodax.platform.contract.input.tag.dto.UpdateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;
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
public class UpdateTagUseCaseImpl implements UpdateTagUseCase {

    TagRepository tagRepository;

    @Override
    public final TagModel update(final UpdateTagDto dto) {
        assertNotNullParameterArgument(dto, "dto");

        final TagModel model = findTagByUidOrThrow(dto.getUid());
        assertTagDoesNotConflictWithName(dto.getName(), model);
        return updateAndSaveTag(dto, model);
    }

    private TagModel findTagByUidOrThrow(final String uid) {
        return tagRepository.getByUid(uid)
                .orElseThrow(() -> new NotFoundException(format("The Tag not found for uid: %s", uid)));
    }

    private void assertTagDoesNotConflictWithName(final String tagName,
                                                  final TagModel model) {
        tagRepository.getByName(tagName)
                .filter(tag -> !tag.getUid().equals(model.getUid()))
                .ifPresent(tag -> {
                    throw new AlreadyExistsException(format("The Tag already exists for name: %s", tagName));
                });
    }

    private TagModel updateAndSaveTag(final UpdateTagDto dto,
                                      final TagModel model) {
        log.debug("Update tag for dto: {}", dto);
        model.update(dto.getName());
        return tagRepository.update(model)
                .orElseThrow(() -> new DbException(format("Unexpected db exception happened during update of tag with uid: %s", dto.getUid())));
    }

}
