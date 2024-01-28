package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static com.samflix.backend.utils.VideoHelper.generateVideoDatabaseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListVideosInteractorTest {

    @Mock
    private VideoGateway videoGateway;

    @InjectMocks
    private ListVideosInteractor interactor;

    @Test
    void list_shouldReturnListOfVideos() {
        int size = 10;
        int page = 0;
        String sortDirection = "asc";
        String sortProperty = "title";
        LocalDate creationDate = LocalDate.of(2024, 1, 25);

        VideoDatabaseEntity video1 = generateVideoDatabaseEntity();
        VideoDatabaseEntity video2 = generateVideoDatabaseEntity();

        when(videoGateway.listVideos(size, page, sortDirection, sortProperty, null, creationDate))
                .thenReturn(Flux.just(new Video(video1), new Video(video2)));

        Flux<Video> videos = interactor.list(size, page, sortDirection, sortProperty, null, creationDate);

        assertEquals(2, videos.count().block());
    }
}
