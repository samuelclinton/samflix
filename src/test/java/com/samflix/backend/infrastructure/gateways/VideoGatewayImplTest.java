package com.samflix.backend.infrastructure.gateways;

import com.samflix.backend.domain.entities.Statistics;
import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.domain.exception.VideoNotFoundException;
import com.samflix.backend.infrastructure.persistence.VideoRepository;
import com.samflix.backend.infrastructure.persistence.model.VideoDatabaseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.samflix.backend.infrastructure.gateways.PageableFactory.createPageable;
import static com.samflix.backend.utils.VideoHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoGatewayImplTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoGatewayImpl videoGateway;

    @Test
    void findByFileName_shouldReturnVideo() {
        VideoDatabaseEntity videoEntity = generateVideoDatabaseEntity();

        when(videoRepository.findByFileName(VIDEO_FILE)).thenReturn(Mono.just(videoEntity));

        Video result = videoGateway.findByFileName(VIDEO_FILE).block();

        assertNotNull(result);
        assertEquals(videoEntity.getId(), result.getId());
    }

    @Test
    void findById_shouldReturnVideo() {
        VideoDatabaseEntity videoEntity = generateVideoDatabaseEntity();

        when(videoRepository.findById(VIDEO_ID)).thenReturn(Mono.just(videoEntity));

        Video result = videoGateway.findById(VIDEO_ID).block();

        assertNotNull(result);
        assertEquals(videoEntity.getId(), result.getId());
    }

    @Test
    void saveVideo_shouldSaveVideo() {
        VideoDatabaseEntity videoEntity = generateVideoDatabaseEntity();
        Video video = new Video(videoEntity);

        when(videoRepository.save(videoEntity)).thenReturn(Mono.just(videoEntity));

        Video result = videoGateway.saveVideo(video).block();

        assertNotNull(result);
        assertEquals(video.getId(), result.getId());
    }

    @Test
    void updateVideo_shouldUpdateVideo() {
        VideoDatabaseEntity videoEntity = generateVideoDatabaseEntity();
        Video updatedVideo = new Video(videoEntity);
        updatedVideo.setTitle(UPDATED_VIDEO_TITLE);
        updatedVideo.setDescription(VIDEO_UPDATED_DESCRIPTION);

        when(videoRepository.findById(VIDEO_ID)).thenReturn(Mono.just(videoEntity));
        when(videoRepository.save(videoEntity)).thenReturn(Mono.just(new VideoDatabaseEntity(updatedVideo)));

        Video result = videoGateway.updateVideo(VIDEO_ID, updatedVideo).block();

        assertNotNull(result);
        assertEquals(updatedVideo.getId(), result.getId());
        assertEquals(updatedVideo.getTitle(), result.getTitle());
        assertEquals(updatedVideo.getDescription(), result.getDescription());
        verify(videoRepository, times(1)).save(videoEntity);
    }

    @Test
    void deleteVideo_shouldDeleteVideo() {
        VideoDatabaseEntity existingVideoEntity = generateVideoDatabaseEntity();

        when(videoRepository.findById(VIDEO_ID)).thenReturn(Mono.just(existingVideoEntity));
        when(videoRepository.delete(existingVideoEntity)).thenReturn(Mono.empty());

        videoGateway.deleteVideo(VIDEO_ID).block();

        verify(videoRepository, times(1)).delete(existingVideoEntity);
    }

    @Test
    void incrementView_shouldIncrementViewsAndSaveVideo() {
        VideoDatabaseEntity videoEntity = generateVideoDatabaseEntity();
        long initialViews = videoEntity.getViews();

        when(videoRepository.findById(VIDEO_ID)).thenReturn(Mono.just(videoEntity));
        when(videoGateway.findVideoByIdOrThrowException(VIDEO_ID)).thenReturn(Mono.just(videoEntity));
        when(videoRepository.save(videoEntity)).thenReturn(Mono.just(videoEntity));

        Video result = videoGateway.incrementView(VIDEO_ID).block();

        assertNotNull(result);
        assertEquals(initialViews + 1, result.getViews());
        verify(videoRepository, times(1)).save(videoEntity);
    }

    @Test
    void listVideos_shouldReturnVideos() {
        int size = 10;
        int page = 0;
        String sortDirection = "desc";
        String sortProperty = "views";
        String title = null;
        LocalDate creationDate = null;
        VideoDatabaseEntity videoEntity = generateVideoDatabaseEntity();
        VideoDatabaseEntity videoEntity2 = generateVideoDatabaseEntity();

        when(videoRepository.findAllByFilter(title, creationDate,
                createPageable(size, page, sortDirection, sortProperty)))
                .thenReturn(Flux.just(videoEntity, videoEntity2));

        Flux<Video> result = videoGateway
                .listVideos(size, page, sortDirection, sortProperty, title, creationDate);

        assertEquals(2, result.count().block());
        Video firstVideo = result.blockFirst();
        assertNotNull(firstVideo);
        assertEquals(videoEntity.getId(), firstVideo.getId());
    }

    @Test
    void findTopLikedByCategory_shouldReturnVideos() {
        int size = 10;
        int page = 0;
        String sortDirection = "DESC";
        String sortProperty = "views";
        VideoDatabaseEntity videoEntity = generateVideoDatabaseEntity();
        VideoDatabaseEntity videoEntity2 = generateVideoDatabaseEntity();

        when(videoRepository.findTopLikedVideosByCategory(VIDEO_CATEGORY_NAME,
                createPageable(size, page, sortDirection, sortProperty)))
                .thenReturn(Flux.just(videoEntity, videoEntity2));

        Flux<Video> result = videoGateway
                .findTopLikedByCategory(VIDEO_CATEGORY_NAME, size, page, sortDirection, sortProperty);

        assertEquals(2, result.count().block());
        Video firstVideo = result.blockFirst();
        assertNotNull(firstVideo);
        assertEquals(videoEntity.getId(), firstVideo.getId());
    }

    @Test
    void getVideoStatistics_shouldReturnStatistics() {
        long totalVideos = 100;
        long totalLikedVideos = 50;
        long totalViews = 1000;
        double averageViews = 10.0;
        ViewStatsAggregation viewStatsAggregation = new ViewStatsAggregation(totalViews, averageViews);

        when(videoRepository.count()).thenReturn(Mono.just(totalVideos));
        when(videoRepository.countByLikedTrue()).thenReturn(Mono.just(totalLikedVideos));
        when(videoRepository.viewStats()).thenReturn(Mono.just(viewStatsAggregation));

        Statistics result = videoGateway.getVideoStatistics().block();

        assertNotNull(result);
        assertEquals(totalVideos, result.totalVideos());
        assertEquals(totalLikedVideos, result.likedVideos());
        assertEquals(totalViews, result.totalViews());
        assertEquals(averageViews, result.averageViews());
    }

    @Test
    void findByFileName_shouldThrowVideoNotFoundExceptionWhenVideoNotFound() {
        String fileName = "invalid-file-name";

        when(videoRepository.findByFileName(fileName))
                .thenReturn(Mono.error(new VideoNotFoundException(
                        "Vídeo não encontrado com o nome de arquivo " + fileName)));

        assertThrows(VideoNotFoundException.class, () -> videoGateway.findByFileName(fileName).block());
    }

    @Test
    void findById_shouldThrowVideoNotFoundExceptionWhenVideoNotFound() {
        String videoId = "invalid-video-id";

        when(videoRepository.findById(videoId)).thenReturn(
                Mono.error(new VideoNotFoundException("Nenhum vídeo encontrado com o ID " + videoId)));

        assertThrows(VideoNotFoundException.class, () -> videoGateway.findById(videoId).block());
    }

}
