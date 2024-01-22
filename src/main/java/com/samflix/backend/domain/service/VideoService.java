package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.NewVideoDto;
import com.samflix.backend.api.controller.model.UpdateVideoDto;
import com.samflix.backend.domain.model.Video;
import reactor.core.publisher.Mono;

public interface VideoService {

    Mono<Video> get(String videoId);
    Mono<Video> create(NewVideoDto newVideoDto);
    Mono<Video> update(String videoId, UpdateVideoDto updateVideoDto);
    Mono<Void> delete(String videoId);

}
