package com.samflix.backend.domain.model;

import java.time.Instant;

public class Video {

    private String title;
    private String description;
    private String url;
    private VideoStatus status;
    private Instant creationDate;
    private Long views;
    private Long likes;
    private User creator;
    private Category category;

}
