package com.samflix.backend.application.controller;

import com.samflix.backend.application.usecases.VideoLikeAndDislikeUseCase;
import com.samflix.backend.external.api.openapi.VideoLikeControllerV1;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/videos/{videoId}/like")
public class VideoLikeControllerV1Impl implements VideoLikeControllerV1 {

    private final VideoLikeAndDislikeUseCase videoLikeAndDislikeUseCase;

    public VideoLikeControllerV1Impl(VideoLikeAndDislikeUseCase videoLikeAndDislikeUseCase) {
        this.videoLikeAndDislikeUseCase = videoLikeAndDislikeUseCase;
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> likeVideo(@PathVariable String videoId) {
        return videoLikeAndDislikeUseCase.like(videoId);
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> dislikeVideo(@PathVariable String videoId) {
        return videoLikeAndDislikeUseCase.dislike(videoId);
    }

}
