package com.samflix.backend.infrastructure.persistence.model;

import com.samflix.backend.domain.entities.Video;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "videos")
public class VideoDatabaseEntity {

    @Id
    private String id;
    private String title;
    private String description;
    private Long views;
    private Boolean liked;
    private String category;
    private String fileName;
    private LocalDate creationDate;

    public VideoDatabaseEntity() {
    }

    public VideoDatabaseEntity(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.views = video.getViews();
        this.liked = video.getLiked();
        this.category = video.getCategory();
        this.fileName = video.getFileName();
        this.creationDate = video.getCreationDate();
    }

    public void incrementViews() {
        this.views += 1;
    }

    public String getId() {
        return id;
    }

    public VideoDatabaseEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public VideoDatabaseEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VideoDatabaseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getViews() {
        return views;
    }

    public VideoDatabaseEntity setViews(Long views) {
        this.views = views;
        return this;
    }

    public Boolean getLiked() {
        return liked;
    }

    public VideoDatabaseEntity setLiked(Boolean liked) {
        this.liked = liked;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public VideoDatabaseEntity setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public VideoDatabaseEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public VideoDatabaseEntity setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

}
