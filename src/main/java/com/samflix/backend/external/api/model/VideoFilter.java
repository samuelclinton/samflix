package com.samflix.backend.external.api.model;

import java.time.LocalDate;

public class VideoFilter {

    private String title;
    private LocalDate creationDate;

    public String getTitle() {
        return title;
    }

    public VideoFilter setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public VideoFilter setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

}
