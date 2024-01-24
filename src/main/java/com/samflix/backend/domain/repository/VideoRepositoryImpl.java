package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.filter.VideoFilter;
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
    public Flux<Video> findByFilter(VideoFilter videoFilter, Pageable pageable) {

        Query query = new Query();

        String title = videoFilter.getTitle();
        LocalDate creationDate = videoFilter.getCreationDate();

        if (title != null) {
            query.addCriteria(Criteria.where("title").regex(".*" + title + ".*", "i"));
        }

        if (creationDate != null) {
            query.addCriteria(Criteria.where("creationDate").is(creationDate));
        }

        query.with(pageable);

        return reactiveMongoTemplate.find(query, Video.class);
    }

    public VideoRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

}
