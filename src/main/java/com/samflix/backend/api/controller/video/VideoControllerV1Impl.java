package com.samflix.backend.api.controller.video;

import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/videos")
public class VideoControllerV1Impl implements VideoControllerV1 {

    @Override
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Video create() {
        return null;
    }

    @Override
    @PutMapping(
            value = "/{videoId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Video update(@PathVariable String videoId) {
        return null;
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
    public void delete(@PathVariable String videoId) {

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

}
