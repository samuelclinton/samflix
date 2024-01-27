package com.samflix.backend.infrastructure.persistence;

import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public class VideoRepositoryImpl implements VideoRepositoryCustom {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<VideoDatabaseEntity> findAllByFilter(String title, LocalDate creationDate, Pageable pageable) {

        Query query = new Query();

        if (title != null) {
            query.addCriteria(Criteria.where("title").regex(".*" + title + ".*", "i"));
        }

        if (creationDate != null) {
            query.addCriteria(Criteria.where("creationDate").is(creationDate));
        }

        query.with(pageable);

        return reactiveMongoTemplate.find(query, VideoDatabaseEntity.class);
    }

    public VideoRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

}
