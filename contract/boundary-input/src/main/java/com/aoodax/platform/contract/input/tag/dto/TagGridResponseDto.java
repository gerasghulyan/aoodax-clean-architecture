package com.aoodax.platform.contract.input.tag.dto;

import com.aoodax.platform.contract.model.tag.TagModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Builder
public class TagGridResponseDto {

    List<TagModel> tags;
    long total;
    
}
