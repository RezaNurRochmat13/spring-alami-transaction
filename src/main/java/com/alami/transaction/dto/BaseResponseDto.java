package com.alami.transaction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class BaseResponseDto implements Serializable {
    private Object data;
    private MetaResponseDto meta;
}
