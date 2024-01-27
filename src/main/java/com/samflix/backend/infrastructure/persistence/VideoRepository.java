package com.samflix.backend.infrastructure.persistence;

import com.samflix.backend.infrastructure.gateways.ViewStatsAggregation;
import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoRepository extends ReactiveMongoRepository<VideoDatabaseEntity, String>, VideoRepositoryCustom {

    Mono<Long> countByLikedTrue();
    Mono<VideoDatabaseEntity> findByFileName(String fileName);
    Flux<VideoDatabaseEntity> findTopLikedVideosByCategory(String category, Pageable pageable);

    @Aggregation("{ $group: { _id: null, totalViews: { $sum: '$views' }, averageViews: { $avg: '$views' } } }")
    Mono<ViewStatsAggregation> viewStats();

}
