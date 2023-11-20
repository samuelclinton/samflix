package com.samflix.backend.api.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApiStatus {

    @NotBlank
    @Schema(example = "Healthy")
    private final String status = "Healthy";

}
