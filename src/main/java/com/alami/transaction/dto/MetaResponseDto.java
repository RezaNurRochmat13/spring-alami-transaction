package com.alami.transaction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MetaResponseDto {
    private Integer page;
    private Integer count;
    private Long total;
    private Integer currentPage;
}
