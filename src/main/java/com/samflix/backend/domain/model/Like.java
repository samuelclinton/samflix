package com.samflix.backend.domain.model;

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
@Document(collection = "likes")
public class Like {

    @Id
    private String id;
    private String userId;
    private String videoId;
    private String videoCategory;

}
