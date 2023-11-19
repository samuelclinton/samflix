package com.samflix.backend.domain.model;

import java.time.Instant;

public class Video {

    private String title;
    private String description;
    private String url;
    private Instant creationDate;
    private Long viewCount;
    private Long favoriteCount;
    private User creator;
    private Category category;

}
