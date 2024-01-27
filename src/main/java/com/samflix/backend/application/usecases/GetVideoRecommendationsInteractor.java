package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.LikeGateway;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import reactor.core.publisher.Flux;

public class GetVideoRecommendationsInteractor implements GetVideoRecommendationsUseCase {

    private final VideoGateway videoGateway;
    private final LikeGateway likeGateway;

    public GetVideoRecommendationsInteractor(VideoGateway videoGateway, LikeGateway likeGateway) {
        this.videoGateway = videoGateway;
        this.likeGateway = likeGateway;
    }

    @Override
    public Flux<Video> getRecommendations(int size, int page, String sortDirection, String sortProperty) {
        return likeGateway.getMostLikedCategory()
                .flatMapMany(category -> videoGateway.findTopLikedByCategory(category, size, page, sortDirection, sortProperty));
    }

}
