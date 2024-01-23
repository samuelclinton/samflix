package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.model.Report;
import com.samflix.backend.domain.model.Video;
import reactor.core.publisher.Mono;

public interface VideoService {

    Video get(String videoId);
    void addLike(Video video);
    void removeLike(Video video);
    Mono<Video> create(NewVideoDto newVideoDto);
    Video update(String videoId, UpdateVideoDto updateVideoDto);
    Mono<Void> delete(String videoId);

    void deleteAllVideosByUser(String userId);
    Mono<Report> getVideoStats();
}
