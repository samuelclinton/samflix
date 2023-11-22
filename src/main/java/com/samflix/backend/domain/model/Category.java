package com.samflix.backend.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Document
public class Category {

    @Id
    private String id;

    @NotBlank
    private String name;

}
