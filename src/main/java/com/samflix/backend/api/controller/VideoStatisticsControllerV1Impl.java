package com.samflix.backend.api.controller;

import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/videos/statistics")
public class VideoStatisticsControllerV1Impl implements VideoStatisticsControllerV1 {

    private final VideoService videoService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Report> statistics() {
        return videoService.getVideoStats();
    }

    public VideoStatisticsControllerV1Impl(VideoService videoService) {
        this.videoService = videoService;
    }

}
