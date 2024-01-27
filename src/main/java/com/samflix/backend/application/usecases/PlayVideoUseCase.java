package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import reactor.core.publisher.Mono;

public interface PlayVideoUseCase {

    Mono<Video> play(String videoFileName);

}
