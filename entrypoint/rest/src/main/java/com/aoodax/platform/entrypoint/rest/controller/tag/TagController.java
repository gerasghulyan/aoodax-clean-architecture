package com.aoodax.platform.entrypoint.rest.controller.tag;

import com.aoodax.platform.contract.input.tag.CreateTagUseCase;
import com.aoodax.platform.contract.input.tag.GetByIdTagUseCase;
import com.aoodax.platform.contract.input.tag.dto.CreateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.entrypoint.rest.common.response.uid.UidAwareResponse;
import com.aoodax.platform.entrypoint.rest.controller.tag.request.CreateTagRequest;
import com.aoodax.platform.entrypoint.rest.controller.tag.response.TagResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    private TagResponse convertTagModelToResponse(final TagModel tag) {
        return TagResponse.builder().uid(tag.getUid()).name(tag.getName()).build();
    }

}
