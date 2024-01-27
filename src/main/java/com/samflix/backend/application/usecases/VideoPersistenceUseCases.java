package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import reactor.core.publisher.Mono;

public interface VideoPersistenceUseCases {

    Mono<Video> create(Video video);
    Mono<Video> update(String videoId, Video video);
    Mono<Void> delete(String videoId);

}
