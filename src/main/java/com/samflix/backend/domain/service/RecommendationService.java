package com.samflix.backend.domain.service;

import com.samflix.backend.domain.model.Video;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface RecommendationService {

    Flux<Video> getRecommendedVideos(Pageable pageable);

}
