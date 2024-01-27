package com.samflix.backend.application.usecases;

import reactor.core.publisher.Mono;

public interface VideoLikeAndDislikeUseCase {

    Mono<Void> like(String videoId);
    Mono<Void> dislike(String videoId);

}
