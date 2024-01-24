package com.samflix.backend.domain.service;

import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.LikeRepository;
import com.samflix.backend.domain.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final VideoRepository videoRepository;
    private final LikeRepository likeRepository;

    @Override
    public Flux<Video> getRecommendedVideos(String userId, Pageable pageable) {
        Mono<String> mostLikedCategory = likeRepository.findMostLikedCategoryByUserId(userId);
        return mostLikedCategory
                .flatMapMany(category -> {
                    log.info(category);
                    return videoRepository.findTopLikedVideosByCategory(category, pageable);
                });
    }

    public RecommendationServiceImpl(VideoRepository videoRepository, LikeRepository likeRepository) {
        this.videoRepository = videoRepository;
        this.likeRepository = likeRepository;
    }

}
