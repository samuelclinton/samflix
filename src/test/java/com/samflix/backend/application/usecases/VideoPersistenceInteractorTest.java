package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.VideoHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoPersistenceInteractorTest {

    @Mock
    private VideoGateway videoGateway;

    @InjectMocks
    private VideoPersistenceInteractor interactor;

    @Test
    void create_shouldCreateVideo() {
        Video video = new Video(generateVideoDatabaseEntity());

        when(videoGateway.saveVideo(video)).thenReturn(Mono.just(video));

        Video createdVideo = interactor.create(video).block();

        verify(videoGateway, times(1)).saveVideo(video);
        assertNotNull(createdVideo);
        assertEquals(video.getId(), createdVideo.getId());
    }

    @Test
    void update_shouldUpdateVideo() {
        Video video = new Video(generateVideoDatabaseEntity());
        video.setTitle(UPDATED_VIDEO_TITLE);
        video.setDescription(VIDEO_UPDATED_DESCRIPTION);

        when(videoGateway.updateVideo(VIDEO_ID, video)).thenReturn(Mono.just(video));

        Video updatedVideo = interactor.update(VIDEO_ID, video).block();

        verify(videoGateway, times(1)).updateVideo(VIDEO_ID, video);
        assertNotNull(updatedVideo);
        assertEquals(UPDATED_VIDEO_TITLE, updatedVideo.getTitle());
        assertEquals(VIDEO_UPDATED_DESCRIPTION, updatedVideo.getDescription());
    }

    @Test
    void delete_shouldDeleteVideo() {
        when(videoGateway.deleteVideo(VIDEO_ID)).thenReturn(Mono.empty());
        interactor.delete(VIDEO_ID).block();
        verify(videoGateway, times(1)).deleteVideo(VIDEO_ID);
    }

}
