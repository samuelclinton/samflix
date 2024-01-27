package com.samflix.backend.domain.entities;

import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;

import java.time.LocalDate;


public class Video {

    private String id;
    private String title;
    private String description;
    private Long views;
    private Boolean liked;
    private String category;
    private String fileName;
    private LocalDate creationDate;

    public Video(String title, String description, String category, String fileName) {
        this.title = title;
        this.description = description;
        this.views = 0L;
        this.liked = false;
        this.category = category;
        this.fileName = fileName;
        this.creationDate = LocalDate.now();
    }

    public Video(VideoDatabaseEntity databaseEntity) {
        this.id = databaseEntity.getId();
        this.title = databaseEntity.getTitle();
        this.description = databaseEntity.getDescription();
        this.views = databaseEntity.getViews();
        this.liked = databaseEntity.getLiked();
        this.category = databaseEntity.getCategory();
        this.fileName = databaseEntity.getFileName();
        this.creationDate = databaseEntity.getCreationDate();
    }

    public String getTitle() {
        return title;
    }

    public Video setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Video setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getViews() {
        return views;
    }

    public Video setViews(Long views) {
        this.views = views;
        return this;
    }

    public Boolean getLiked() {
        return liked;
    }

    public Video setLiked(Boolean liked) {
        this.liked = liked;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Video setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public Video setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Video setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public Video setId(String id) {
        this.id = id;
        return this;
    }
}
