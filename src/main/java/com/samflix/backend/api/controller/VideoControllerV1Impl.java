package com.samflix.backend.api.controller;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.filter.VideoFilter;
import com.samflix.backend.domain.service.RecommendationService;
import com.samflix.backend.domain.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/videos")
public class VideoControllerV1Impl implements VideoControllerV1 {

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    private final VideoService videoService;
    private final RecommendationService recommendationService;

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Video> create(@Valid NewVideoDto newVideoDto) {
        return videoService.create(newVideoDto);
    }

    @Override
    @PutMapping(value = "/{videoId}", consumes = JSON, produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public Video update(@PathVariable String videoId, @RequestBody @Valid UpdateVideoDto updateVideoDto) {
        return videoService.update(videoId, updateVideoDto);
    }

    @Override
    @GetMapping(value = "/{videoId}", produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public Video play(@PathVariable String videoId) {
        return null;
    }

    @Override
    @GetMapping(produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Video> list(VideoFilter filter, Pageable pageable) {
        return videoService.getAllVideos(filter, pageable);
    }

    @Override
    @GetMapping("/recommendations")
    public Flux<Video> getRecommendations(Pageable pageable) {
        return recommendationService.getRecommendedVideos(pageable);
    }

    @Override
    @DeleteMapping("/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String videoId) {
        return videoService.delete(videoId);
    }

    public VideoControllerV1Impl(VideoService videoService, RecommendationService recommendationService) {
        this.videoService = videoService;
        this.recommendationService = recommendationService;
    }

}
