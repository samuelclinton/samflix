package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.filter.VideoFilter;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface VideoRepositoryCustom {

    Flux<Video> findByFilter(VideoFilter videoFilter, Pageable pageable);

}
