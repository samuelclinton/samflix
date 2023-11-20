package com.samflix.backend.api.controller;

import com.samflix.backend.api.controller.model.ApiStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Status")
@RestController
@RequestMapping("/status")
public class HealthController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiStatus> status() {
        return ResponseEntity.ok(new ApiStatus());
    }

}
