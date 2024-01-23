package com.samflix.backend.api.controller.video;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/videos")
public class VideoControllerV1Impl implements VideoControllerV1 {

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    private final VideoService videoService;

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Video> create(@Valid NewVideoDto newVideoDto) {
        return videoService.create(newVideoDto);
    }

    @Override
    @PutMapping(
            value = "/{videoId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Video update(@PathVariable String videoId, @RequestBody @Valid UpdateVideoDto updateVideoDto) {
        return videoService.update(videoId, updateVideoDto);
    }

    @Override
    @GetMapping(
            value = "/{videoId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Video play(@PathVariable String videoId) {
        return null;
    }

    @Override
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Page<Video> list(Pageable pageable) {
        return null;
    }

    @Override
    @DeleteMapping("/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String videoId) {
        return videoService.delete(videoId);
    }

    @Override
    @GetMapping(
            value = "/statistics",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Report statistics() {
        return null;
    }

    public VideoControllerV1Impl(VideoService videoService) {
        this.videoService = videoService;
    }

}
