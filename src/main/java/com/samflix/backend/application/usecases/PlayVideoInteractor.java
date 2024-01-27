package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import reactor.core.publisher.Mono;

public class PlayVideoInteractor implements PlayVideoUseCase {

    private final VideoGateway videoGateway;

    public PlayVideoInteractor(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    @Override
    public Mono<Video> play(String videoFileName) {
        return videoGateway.findByFileName(videoFileName)
                .flatMap(video -> videoGateway.incrementView(video.getId()));
    }

}
