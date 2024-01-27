package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import reactor.core.publisher.Mono;

public class VideoPersistenceInteractor implements VideoPersistenceUseCases {

    private final VideoGateway videoGateway;

    public VideoPersistenceInteractor(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    @Override
    public Mono<Video> create(Video video) {
        return videoGateway.saveVideo(video);
    }

    @Override
    public Mono<Video> update(String videoId, Video video) {
        return videoGateway.updateVideo(videoId, video);
    }

    @Override
    public Mono<Void> delete(String videoId) {
        return videoGateway.deleteVideo(videoId);
    }

}
