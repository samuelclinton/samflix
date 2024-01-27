package com.samflix.backend.external.api.model;

public class UpdateVideoDto {

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public UpdateVideoDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateVideoDto setDescription(String description) {
        this.description = description;
        return this;
    }

}
