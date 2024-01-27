package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Statistics;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import reactor.core.publisher.Mono;

public class GetVideoStatisticsInteractor implements GetVideoStatisticsUseCase {

    private final VideoGateway videoGateway;

    public GetVideoStatisticsInteractor(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    @Override
    public Mono<Statistics> getVideoStatistics() {
        return videoGateway.getVideoStatistics();
    }

}
