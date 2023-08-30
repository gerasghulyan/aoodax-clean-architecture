package com.aoodax.platform.usecase.tag;

import com.aoodax.platform.contract.input.common.PaginationAwareDto;
import com.aoodax.platform.contract.input.tag.FindTagsUseCase;
import com.aoodax.platform.contract.input.tag.dto.TagGridResponseDto;
import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.contract.output.tag.TagRepository;
import com.aoodax.platform.contract.output.tag.mapper.TagModelDocumentMapper;
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class FindTagsUseCaseImpl implements FindTagsUseCase {

    TagRepository tagRepository;

    @Override
    public TagGridResponseDto find(final PaginationAwareDto pagination) {
        assertNotNullParameterArgument(pagination, "pagination");

        log.debug("Retrieving tags for pageable: {}", pagination);
        final Page<TagEntity> pagedTag = tagRepository.find(PageRequest.of(pagination.getPage(), pagination.getSize()));
        final List<TagModel> tags = pagedTag.stream().map(TagModelDocumentMapper::toModel).toList();
        log.debug("Tags successfully retrieving for pageable: {} with tags - {}", pagination, pagedTag);
        return TagGridResponseDto.builder().tags(tags).total(pagedTag.getTotalElements()).build();
    }
}
