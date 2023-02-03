package com.pc.hamed.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class SampleDto implements Serializable {

    private String source;
    private String codeListCode;
    private String code;
    private String displayName;
    private String longDescription;
    private String toDate;
    private Integer sortingPriority;

    public SampleDto(String source, String codeListCode, String code, String displayName, String longDescription, String toDate, Integer sortingPriority) {
        this.source = source;
        this.codeListCode = codeListCode;
        this.code = code;
        this.displayName = displayName;
        this.longDescription = longDescription;
        this.toDate = toDate;
        this.sortingPriority = sortingPriority;
    }
}
