package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import reactor.core.publisher.Flux;

public interface GetVideoRecommendationsUseCase {

    Flux<Video> getRecommendations(int size, int page, String sortDirection, String sortProperty);

}
