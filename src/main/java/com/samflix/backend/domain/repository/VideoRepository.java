package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VideoRepository extends ReactiveMongoRepository<Video, String> {
}
