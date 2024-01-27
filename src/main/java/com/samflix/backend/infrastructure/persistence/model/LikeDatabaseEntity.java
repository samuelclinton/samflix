package com.samflix.backend.infrastructure.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "likes")
public class LikeDatabaseEntity {

    @Id
    private String id;
    private String videoId;
    private String videoCategory;

    public LikeDatabaseEntity(String videoId, String videoCategory) {
        this.videoId = videoId;
        this.videoCategory = videoCategory;
    }

    public String getId() {
        return id;
    }

    public LikeDatabaseEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getVideoId() {
        return videoId;
    }

    public LikeDatabaseEntity setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public String getVideoCategory() {
        return videoCategory;
    }

    public LikeDatabaseEntity setVideoCategory(String videoCategory) {
        this.videoCategory = videoCategory;
        return this;
    }

}
