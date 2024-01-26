package com.samflix.backend.api.controller.model;

import com.samflix.backend.config.validation.FileContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
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

}
