package com.aoodax.platform.entrypoint.rest.controller.tag;

import com.aoodax.platform.contract.input.tag.CreateTagUseCase;
import com.aoodax.platform.contract.input.tag.DeleteTagUseCase;
import com.aoodax.platform.contract.input.tag.GetByIdTagUseCase;
import com.aoodax.platform.contract.input.tag.UpdateTagUseCase;
import com.aoodax.platform.contract.input.tag.dto.CreateTagDto;
import com.aoodax.platform.contract.input.tag.dto.UpdateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.entrypoint.rest.common.response.uid.UidAwareResponse;
import com.aoodax.platform.entrypoint.rest.controller.tag.request.CreateTagRequest;
import com.aoodax.platform.entrypoint.rest.controller.tag.request.UpdateTagRequest;
import com.aoodax.platform.entrypoint.rest.controller.tag.response.TagResponse;
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

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/tags")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TagController {

    CreateTagUseCase createTagUseCase;
    GetByIdTagUseCase getByIdTagUseCase;
    UpdateTagUseCase updateTagUseCase;
    DeleteTagUseCase deleteTagUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UidAwareResponse create(@Valid @RequestBody final CreateTagRequest request) {
        assertNotNullParameterArgument(request, "CreateTagRequest");

        log.info("Creating tag for request: {}", request);
        final CreateTagDto dto = CreateTagDto.builder()
                .name(request.getName())
                .build();
        final TagModel tag = createTagUseCase.create(dto);
        log.info("Tag successfully created with model: {}", tag);
        return convertTagModelToResponse(tag);
    }

    @GetMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponse getById(@Valid @PathVariable final String uid) {
        log.info("Retrieving tag for uid: {}", uid);
        final TagModel tag = getByIdTagUseCase.getById(uid);
        log.info("Tag successfully retrieved with model: {}", tag);
        return convertTagModelToResponse(tag);
    }

    @PutMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponse update(@Valid @PathVariable final String uid, @Valid @RequestBody final UpdateTagRequest request) {
        log.info("Update tag for request: {}", request);
        final UpdateTagDto dto = UpdateTagDto.builder()
                .uid(uid)
                .name(request.getName())
                .build();
        final TagModel tag = updateTagUseCase.update(dto);
        log.info("Tag successfully updated with model: {}", tag);
        return convertTagModelToResponse(tag);
    }

    @DeleteMapping("/{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
