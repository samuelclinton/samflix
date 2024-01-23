package com.samflix.backend.api.controller.model;

import com.samflix.backend.core.validation.FileContentType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class NewVideoDto {

    private String title;
    private String description;
    private String creatorUsername;
    private String category;

    @NotNull
    @FileContentType(allowed = {"video/mp4"})
    private MultipartFile videoFile;

}
