package com.aoodax.platform.entrypoint.rest.api.controller.tag;

import com.aoodax.jvm.common.rest.dto.request.PageableRequest;
import com.aoodax.platform.contract.input.common.PaginationAwareDto;
import com.aoodax.platform.contract.input.tag.CreateTagUseCase;
import com.aoodax.platform.contract.input.tag.DeleteTagUseCase;
import com.aoodax.platform.contract.input.tag.FindTagsUseCase;
import com.aoodax.platform.contract.input.tag.GetByIdTagUseCase;
import com.aoodax.platform.contract.input.tag.UpdateTagUseCase;
import com.aoodax.platform.contract.input.tag.dto.CreateTagDto;
import com.aoodax.platform.contract.input.tag.dto.TagGridResponseDto;
import com.aoodax.platform.contract.input.tag.dto.UpdateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.entrypoint.rest.dto.tag.request.CreateTagRequest;
import com.aoodax.platform.entrypoint.rest.dto.tag.request.UpdateTagRequest;
import com.aoodax.platform.entrypoint.rest.dto.tag.response.TagGridResponse;
import com.aoodax.platform.entrypoint.rest.dto.tag.response.TagResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@Tag(name = "Tags API")
@RequestMapping("/v1/tags")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TagController {

    CreateTagUseCase createTagUseCase;
    GetByIdTagUseCase getByIdTagUseCase;
    FindTagsUseCase findTagsUseCase;
    UpdateTagUseCase updateTagUseCase;
    DeleteTagUseCase deleteTagUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create")
    public TagResponse create(@Valid @RequestBody final CreateTagRequest request) {
        log.info("Creating tag for request: {}", request);
        final CreateTagDto dto = CreateTagDto.builder().name(request.name()).build();
        final TagModel tag = createTagUseCase.create(dto);
        log.info("Tag successfully created with model: {}", tag);
        return convertTagModelToResponse(tag);
    }

    @GetMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get by uid")
    public TagResponse getByUid(@Valid @PathVariable final String uid) {
        log.info("Retrieving tag for uid: {}", uid);
        final TagModel tag = getByIdTagUseCase.getById(uid);
        log.info("Tag successfully retrieved with model: {}", tag);
        return convertTagModelToResponse(tag);
    }

    @PostMapping("find")
    @Operation(description = "Find by Post")
    @ResponseStatus(HttpStatus.OK)
    public TagGridResponse find(@Valid @RequestBody final PageableRequest request) {
        log.info("Retrieving all tags with pagination for request - {}", request);
        final PaginationAwareDto paginationAwareDto = PaginationAwareDto.builder()
                .page(request.getPage() - 1)
                .size(request.getSize())
                .build();
        final TagGridResponseDto tags = findTagsUseCase.find(paginationAwareDto);
        log.info("All tags with pagination successfully retrieved");
        final List<TagResponse> tagsResponse = tags.getItems().stream()
                .map(this::convertTagModelToResponse)
                .collect(Collectors.toList());
        return TagGridResponse.builder().data(tagsResponse).total(tags.getTotal()).build();
    }

    @PutMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Update")
    public TagResponse update(@Valid @PathVariable final String uid,
                              @Valid @RequestBody final UpdateTagRequest request) {
        log.info("Update tag for request: {}", request);
        final UpdateTagDto dto = UpdateTagDto.builder().uid(uid).name(request.name()).build();
        final TagModel tag = updateTagUseCase.update(dto);
        log.info("Tag successfully updated with model: {}", tag);
        return convertTagModelToResponse(tag);
    }

    @DeleteMapping("/{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete")
    public TagResponse delete(@Valid @PathVariable final String uid) {
        log.info("Deleting tag for uid: {}", uid);
        final TagModel tag = deleteTagUseCase.delete(uid);
        log.info("Tag successfully deleted with model: {}", tag);
        return convertTagModelToResponse(tag);
    }

    private TagResponse convertTagModelToResponse(final TagModel tag) {
        return TagResponse.builder().uid(tag.getUid()).name(tag.getName()).build();
    }

}
