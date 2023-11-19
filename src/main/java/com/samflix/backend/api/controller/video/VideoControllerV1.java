package com.samflix.backend.api.controller.video;

import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/videos")
public class VideoControllerV1 implements VideoController {

    @Override
    @PostMapping
    public Video create() {
        return null;
    }

    @Override
    @PutMapping("/{videoId}")
    public Video update(@PathVariable String videoId) {
        return null;
    }

    @Override
    @GetMapping("/{videoId}")
    public Video play(@PathVariable String videoId) {
        return null;
    }

    @Override
    @GetMapping
    public Page<Video> list(Pageable pageable) {
        return null;
    }

    @Override
    @DeleteMapping("/{videoId}")
    public void delete(@PathVariable String videoId) {

    }

    @Override
    @GetMapping("/statistics")
    public Report statistics() {
        return null;
    }

}
