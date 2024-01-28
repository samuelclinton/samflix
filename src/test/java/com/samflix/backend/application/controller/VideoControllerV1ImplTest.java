package com.samflix.backend.application.controller;

import com.samflix.backend.application.usecases.GetVideoRecommendationsUseCase;
import com.samflix.backend.application.usecases.ListVideosUseCase;
import com.samflix.backend.application.usecases.PlayVideoUseCase;
import com.samflix.backend.application.usecases.VideoPersistenceUseCases;
import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.external.api.model.NewVideoDto;
import com.samflix.backend.external.api.model.UpdateVideoDto;
import com.samflix.backend.external.api.model.VideoFilter;
import com.samflix.backend.infrastructure.persistence.VideoStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import static com.samflix.backend.utils.VideoHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoControllerV1ImplTest {

    @Mock
    private VideoStorage videoStorage;

    @Mock
    private PlayVideoUseCase playVideoUseCase;

    @Mock
    private ListVideosUseCase listVideosUseCase;

    @Mock
    private VideoPersistenceUseCases videoPersistenceUseCases;


    @Mock
    private GetVideoRecommendationsUseCase getVideoRecommendationsUseCase;

    @InjectMocks
    private VideoControllerV1Impl videoController;

    @Test
    void create_shouldCreateVideo() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("video-example.mp4",
                InputStream.nullInputStream());
        NewVideoDto newVideoDto = new NewVideoDto();
        newVideoDto.setTitle(VIDEO_TITLE);
        newVideoDto.setDescription(VIDEO_DESCRIPTION);
        newVideoDto.setCategory(VIDEO_CATEGORY_NAME);
        newVideoDto.setVideoFile(mockMultipartFile);

        Video video = new Video(
                newVideoDto.getTitle(),
                newVideoDto.getDescription(),
                newVideoDto.getCategory(),
                VIDEO_FILE
        );

        when(videoStorage.storeVideo(any(InputStream.class))).thenReturn(VIDEO_FILE);
        when(videoPersistenceUseCases.create(any(Video.class))).thenReturn(Mono.just(video));

        Video result = videoController.create(newVideoDto).block();

        assertNotNull(result);
        assertEquals(video.getId(), result.getId());
        verify(videoStorage).storeVideo(any(InputStream.class));
        verify(videoPersistenceUseCases, times(1)).create(any(Video.class));
    }

    @Test
    void update_shouldUpdateVideo() {
        UpdateVideoDto updateVideoDto = new UpdateVideoDto();
        updateVideoDto.setTitle(UPDATED_VIDEO_TITLE);
        updateVideoDto.setDescription(VIDEO_UPDATED_DESCRIPTION);

        Video video = new Video(
                updateVideoDto.getTitle(),
                updateVideoDto.getDescription(),
                null,
                null
        );

        when(videoPersistenceUseCases.update(eq(VIDEO_ID), any(Video.class))).thenReturn(Mono.just(video));

        Video result = videoController.update(VIDEO_ID, updateVideoDto).block();

        assertNotNull(result);
        assertEquals(video.getId(), result.getId());
        assertEquals(video.getTitle(), result.getTitle());
        assertEquals(video.getDescription(), result.getDescription());
        verify(videoPersistenceUseCases, times(1))
                .update(eq(VIDEO_ID), any(Video.class));
    }

    @Test
    void play_shouldPlayVideo() {
        Video video = createExampleVideo();

        when(playVideoUseCase.play(VIDEO_FILE)).thenReturn(Mono.just(video));

        Video result = videoController.play(VIDEO_FILE).block();

        assertNotNull(result);
        verify(playVideoUseCase, times(1)).play(VIDEO_FILE);
    }

    @Test
    void list_shouldListVideos() {
        VideoFilter filter = new VideoFilter();
        filter.setTitle(VIDEO_TITLE);
        filter.setCreationDate(LocalDate.now());
        int page = 0;
        int size = 10;
        String property = "views";
        String order = "desc";

        Video video = createExampleVideo();

        when(listVideosUseCase.list(anyInt(), anyInt(), anyString(), anyString(), anyString(), any(LocalDate.class)))
                .thenReturn(Flux.just(video));

        Flux<Video> result = videoController.list(filter, page, size, property, order);

        assertNotNull(result.collectList().block());

        verify(listVideosUseCase).list(eq(size), eq(page), eq(order), eq(property), anyString(), any(LocalDate.class));
    }

    @Test
    void getRecommendations_shouldGetVideoRecommendations() {
        int page = 0;
        int size = 10;
        String property = "views";
        String order = "desc";
        Video video = createExampleVideo();

        when(getVideoRecommendationsUseCase.getRecommendations(anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(Flux.just(video));

        Flux<Video> result = videoController.getRecommendations(page, size, property, order);

        assertNotNull(result.collectList().block());
        verify(getVideoRecommendationsUseCase).getRecommendations(size, page, order, property);
    }

    @Test
    void delete_shouldDeleteVideo() {
        when(videoPersistenceUseCases.delete(VIDEO_ID)).thenReturn(Mono.empty());

        Mono<Void> result = videoController.delete(VIDEO_ID);

        assertDoesNotThrow(() -> result.block());
        verify(videoPersistenceUseCases, times(1)).delete(VIDEO_ID);
    }

    private Video createExampleVideo() {
        return new Video(VIDEO_TITLE, VIDEO_DESCRIPTION, VIDEO_CATEGORY_NAME, VIDEO_FILE);
    }

}
