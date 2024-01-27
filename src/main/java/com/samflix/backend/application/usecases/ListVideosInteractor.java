package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public class ListVideosInteractor implements ListVideosUseCase {

    private final VideoGateway videoGateway;

    public ListVideosInteractor(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    @Override
    public Flux<Video> list(int size, int page, String sortDirection, String sortProperty, String title, LocalDate creationDate) {
        return videoGateway.listVideos(size, page, sortDirection, sortProperty, title, creationDate);
    }

}
