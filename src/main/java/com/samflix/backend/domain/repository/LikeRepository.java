package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {
    Mono<Like> findByUserIdAndVideoId(String userId, String videoId);
    Flux<Like> findAllByUserId(String userId);
}
