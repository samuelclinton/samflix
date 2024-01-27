package com.samflix.backend.infrastructure.gateways;

import com.samflix.backend.domain.entities.Like;
import reactor.core.publisher.Mono;

public interface LikeGateway {

    Mono<String> getMostLikedCategory();
    Mono<Like> findByVideoId(String videoId);

    Mono<Void> saveLike(Like like);

    Mono<Void> deleteLike(String videoId);

}
