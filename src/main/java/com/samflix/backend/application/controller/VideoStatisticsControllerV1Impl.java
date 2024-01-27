package com.samflix.backend.application.controller;

import com.samflix.backend.application.usecases.GetVideoStatisticsUseCase;
import com.samflix.backend.domain.entities.Statistics;
import com.samflix.backend.external.api.openapi.VideoStatisticsControllerV1;
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

    private final GetVideoStatisticsUseCase videoStatisticsUseCase;

    public VideoStatisticsControllerV1Impl(GetVideoStatisticsUseCase videoStatisticsUseCase) {
        this.videoStatisticsUseCase = videoStatisticsUseCase;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Statistics> statistics() {
        return videoStatisticsUseCase.getVideoStatistics();
    }

}
