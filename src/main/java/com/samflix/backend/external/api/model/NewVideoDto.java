package com.samflix.backend.external.api.model;

import com.samflix.backend.external.api.validation.FileContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class NewVideoDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String category;

    @NotNull
    @FileContentType(allowed = {"video/mp4"})
    private MultipartFile videoFile;

    public String getTitle() {
        return title;
    }

    public NewVideoDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewVideoDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public NewVideoDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public MultipartFile getVideoFile() {
        return videoFile;
    }

    public NewVideoDto setVideoFile(MultipartFile videoFile) {
        this.videoFile = videoFile;
        return this;
    }
}
