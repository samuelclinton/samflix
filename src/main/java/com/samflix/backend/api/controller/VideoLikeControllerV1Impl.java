package com.samflix.backend.api.controller;

import com.samflix.backend.domain.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/videos/{videoId}/like")
public class VideoLikeControllerV1Impl implements VideoLikeControllerV1 {

    private final LikeService likeService;

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void likeVideo(@PathVariable String videoId) {
        likeService.like(videoId);
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislikeVideo(@PathVariable String videoId) {
        likeService.dislike(videoId);
    }

    public VideoLikeControllerV1Impl(LikeService likeService) {
        this.likeService = likeService;
    }

}
