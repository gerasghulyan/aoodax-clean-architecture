package com.aoodax.platform.contract.input.tag.dto;

import com.aoodax.platform.contract.input.common.AbstractGridAwareResponseDto;
import com.aoodax.platform.contract.model.tag.TagModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TagGridResponseDto extends AbstractGridAwareResponseDto<TagModel> {

    @Builder
    public TagGridResponseDto(final List<TagModel> items,
                              final long total) {
        super(items, total);
    }
}
