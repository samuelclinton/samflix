package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.VideoHelper.VIDEO_FILE;
import static com.samflix.backend.utils.VideoHelper.generateVideoDatabaseEntity;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayVideoInteractorTest {

    @Mock
    private VideoGateway videoGateway;

    @InjectMocks
    private PlayVideoInteractor interactor;

    @Test
    void play_shouldPlayVideoAndIncrementView() {
        Video video = new Video(generateVideoDatabaseEntity());

        when(videoGateway.findByFileName(VIDEO_FILE)).thenReturn(Mono.just(video));
        doAnswer(invocation -> {
            video.setViews(1L);
            return Mono.just(video);
        }).when(videoGateway).incrementView(video.getId());

        Video result = interactor.play(VIDEO_FILE).block();

        assertNotNull(result);
        assertEquals(1L, result.getViews());
    }

}
