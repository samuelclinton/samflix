package com.samflix.backend.infrastructure.persistence;

import com.samflix.backend.infrastructure.persistence.model.LikeDatabaseEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveMongoRepository<LikeDatabaseEntity, String> {

    Mono<LikeDatabaseEntity> findByVideoId(String videoId);

    @Aggregation({
            "{ $group: { _id: '$videoCategory', count: { $sum: 1 } } }",
            "{ $sort: { count: -1 } }",
            "{ $limit: 1 }",
            "{ $project: { _id: 0, category: '$_id' } }"
    })
    Mono<String> findMostLikedCategory();

}
