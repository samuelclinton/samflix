package com.samflix.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collection = "video")
public class Video {

    @Id
    private String id;

    private String title;
    private String description;
    private String url;
    private VideoStatus status;
    private Instant creationDate;
    private Long views;
    private Long likes;

    private User creator;

    @DBRef
    private Category category;

}
