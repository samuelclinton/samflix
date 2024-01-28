package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Statistics;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVideoStatisticsInteractorTest {

    @Mock
    private VideoGateway videoGateway;

    @InjectMocks
    private GetVideoStatisticsInteractor interactor;

    @Test
    void getVideoStatistics_shouldReturnStatisticsWithExpectedValues() {
        Statistics expectedStatistics = new Statistics(100L, 50L, 10000L, 200.5);
        when(videoGateway.getVideoStatistics()).thenReturn(Mono.just(expectedStatistics));

        Statistics statistics = interactor.getVideoStatistics().block();

        assertNotNull(statistics);
        assertNotNull(statistics.totalVideos());
        assertNotNull(statistics.likedVideos());
        assertNotNull(statistics.totalViews());
        assertNotNull(statistics.averageViews());
        assertEquals(expectedStatistics.totalVideos(), statistics.totalVideos());
        assertEquals(expectedStatistics.likedVideos(), statistics.likedVideos());
        assertEquals(expectedStatistics.totalViews(), statistics.totalViews());
        assertEquals(expectedStatistics.averageViews(), statistics.averageViews());
    }
}