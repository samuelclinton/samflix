package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.LikeGateway;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.VideoHelper.generateVideoDatabaseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVideoRecommendationsInteractorTest {

    @Mock
    private VideoGateway videoGateway;

    @Mock
    private LikeGateway likeGateway;

    @InjectMocks
    private GetVideoRecommendationsInteractor interactor;

    @Test
    void getRecommendations_shouldReturnRecommendedVideos() {

        when(likeGateway.getMostLikedCategory())
                .thenReturn(Mono.just("Comédia"));
        when(videoGateway.findTopLikedByCategory("Comédia", 10, 0, "desc", "id"))
                .thenReturn(Flux.just(new Video(generateVideoDatabaseEntity())));

        Flux<Video> recommendations = interactor.getRecommendations(10, 0, "desc", "id");

        assertEquals(1, recommendations.count().block());
    }

}
