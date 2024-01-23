package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface VideoRepository extends ReactiveMongoRepository<Video, String> {
    Flux<Video> findAllByCreatorUsername(String creatorUsername);
}
