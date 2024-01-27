package com.samflix.backend.external.config;

import com.samflix.backend.application.usecases.*;
import com.samflix.backend.infrastructure.gateways.LikeGateway;
import com.samflix.backend.infrastructure.gateways.LikeGatewayImpl;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import com.samflix.backend.infrastructure.gateways.VideoGatewayImpl;
import com.samflix.backend.infrastructure.persistence.LikeRepository;
import com.samflix.backend.infrastructure.persistence.MockVideoStorageImpl;
import com.samflix.backend.infrastructure.persistence.VideoRepository;
import com.samflix.backend.infrastructure.persistence.VideoStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    ListVideosUseCase listVideosUseCase(VideoGateway videoGateway) {
        return new ListVideosInteractor(videoGateway);
    }

    @Bean
    GetVideoRecommendationsUseCase getVideoRecommendationsUseCase(VideoGateway videoGateway, LikeGateway likeGateway) {
        return new GetVideoRecommendationsInteractor(videoGateway, likeGateway);
    }

    @Bean
    GetVideoStatisticsUseCase getVideoStatisticsUseCase(VideoGateway videoGateway) {
        return new GetVideoStatisticsInteractor(videoGateway);
    }

    @Bean
    PlayVideoUseCase playVideoUseCase(VideoGateway videoGateway) {
        return new PlayVideoInteractor(videoGateway);
    }

    @Bean
    VideoLikeAndDislikeUseCase videoLikeAndDislikeUseCase(VideoGateway videoGateway, LikeGateway likeGateway) {
        return new VideoLikeAndDislikeInteractor(videoGateway, likeGateway);
    }

    @Bean
    VideoPersistenceUseCases videoPersistenceUseCases(VideoGateway videoGateway) {
        return new VideoPersistenceInteractor(videoGateway);
    }

    @Bean
    VideoGateway videoGateway(VideoRepository videoRepository) {
        return new VideoGatewayImpl(videoRepository);
    }

    @Bean
    LikeGateway likeGateway(LikeRepository likeRepository) {
        return new LikeGatewayImpl(likeRepository);
    }

    @Bean
    VideoStorage videoStorage() {
        return new MockVideoStorageImpl();
    }

}
