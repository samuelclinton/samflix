package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.model.ViewStats;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoRepository extends ReactiveMongoRepository<Video, String> {

    Flux<Video> findAllByCreatorUsername(String creatorUsername);

    Mono<Long> countByLikesGreaterThan(Long minimumLikes);

    @Aggregation("{ $group: { _id: null, totalViews: { $sum: '$views' }, averageViews: { $avg: '$views' } } }")
    Mono<ViewStats> viewStats();

    Flux<Video> findTopLikedVideosByCategory(String category, Pageable pageable);

}
