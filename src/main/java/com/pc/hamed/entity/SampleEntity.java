package com.pc.hamed.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@Table(name = "sample_entity")
public class SampleEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "source")
    private String source;

    @Column(name = "code_list_code")
    private String codeListCode;

    @Column(name = "code")
    private String code;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "to_date")
    private String toDate;

    @Column(name = "sorting_priority")
    private Integer sortingPriority;

    public SampleEntity(Long id, String source, String codeListCode, String code, String displayName, String longDescription, String toDate, Integer sortingPriority) {
        this.id = id;
        this.source = source;
        this.codeListCode = codeListCode;
        this.code = code;
        this.displayName = displayName;
        this.longDescription = longDescription;
        this.toDate = toDate;
        this.sortingPriority = sortingPriority;
    }

    public SampleEntity() {

    }
}
