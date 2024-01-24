package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Like;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {

    Mono<Like> findByUserIdAndVideoId(String userId, String videoId);
    Flux<Like> findAllByUserId(String userId);

    @Aggregation({
            "{ $match: { 'userId': ?0 } }",
            "{ $group: { _id: '$videoCategory', count: { $sum: 1 } } }",
            "{ $sort: { count: -1 } }",
            "{ $limit: 1 }",
            "{ $project: { _id: 0, category: '$_id' } }"
    })
    Mono<String> findMostLikedCategoryByUserId(String userId);

}
