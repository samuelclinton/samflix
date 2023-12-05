package com.samflix.backend.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "videos")
public class Video {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @EqualsAndHashCode.Include
    private UUID file;

    private String title;
    private String description;
    private VideoStatus status;
    private Instant creationDate;
    private Long views;
    private Long likes;
    private String creatorUsername;
    private String category;

}
