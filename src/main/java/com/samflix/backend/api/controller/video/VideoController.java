package com.samflix.backend.api.controller.video;

import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideoController {

    Video create();
    Video update(String videoId);
    Video play(String videoId);
    Page<Video> list(Pageable pageable);
    void delete(String videoId);
    Report statistics();

}
