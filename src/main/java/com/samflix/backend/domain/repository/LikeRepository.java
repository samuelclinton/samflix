package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Like;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {

    Mono<Like> findByVideoId(String videoId);

    @Aggregation({
            "{ $group: { _id: '$videoCategory', count: { $sum: 1 } } }",
            "{ $sort: { count: -1 } }",
            "{ $limit: 1 }",
            "{ $project: { _id: 0, category: '$_id' } }"
    })
    Mono<String> findMostLikedCategory();

}
