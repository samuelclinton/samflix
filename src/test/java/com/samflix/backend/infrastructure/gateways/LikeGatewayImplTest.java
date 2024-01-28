package com.samflix.backend.infrastructure.gateways;

import com.samflix.backend.domain.entities.Like;
import com.samflix.backend.domain.exception.LikeNotFoundException;
import com.samflix.backend.infrastructure.persistence.LikeRepository;
import com.samflix.backend.infrastructure.persistence.model.LikeDatabaseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.LikeHelper.generateLikeDatabaseEntity;
import static com.samflix.backend.utils.VideoHelper.VIDEO_CATEGORY_NAME;
import static com.samflix.backend.utils.VideoHelper.VIDEO_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeGatewayImplTest {

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private LikeGatewayImpl likeGateway;

    @Test
    void getMostLikedCategory_shouldReturnCategory() {
        when(likeRepository.findMostLikedCategory()).thenReturn(Mono.just(VIDEO_CATEGORY_NAME));

        String result = likeGateway.getMostLikedCategory().block();

        assertNotNull(result);
        assertEquals(VIDEO_CATEGORY_NAME, result);
    }

    @Test
    void findByVideoId_shouldReturnLike() {
        LikeDatabaseEntity likeEntity = generateLikeDatabaseEntity();

        when(likeRepository.findByVideoId(VIDEO_ID)).thenReturn(Mono.just(likeEntity));

        Like result = likeGateway.findByVideoId(VIDEO_ID).block();

        assertNotNull(result);
        assertEquals(likeEntity.getVideoId(), result.videoId());
        assertEquals(likeEntity.getVideoCategory(), result.videoCategory());
    }

    @Test
    void saveLike_shouldSaveLike() {
        LikeDatabaseEntity likeEntity = generateLikeDatabaseEntity();
        Like like = new Like(likeEntity.getVideoId(), likeEntity.getVideoCategory());

        when(likeRepository.save(any(LikeDatabaseEntity.class))).thenReturn(Mono.just(likeEntity));

        Mono<Void> result = likeGateway.saveLike(like);

        assertDoesNotThrow(() -> result.block());
        verify(likeRepository, times(1)).save(any(LikeDatabaseEntity.class));
    }

    @Test
    void deleteLike_shouldDeleteLike() {
        LikeDatabaseEntity likeEntity = generateLikeDatabaseEntity();

        when(likeRepository.findByVideoId(VIDEO_ID)).thenReturn(Mono.just(likeEntity));
        when(likeRepository.delete(likeEntity)).thenReturn(Mono.empty());

        Mono<Void> result = likeGateway.deleteLike(VIDEO_ID);

        assertDoesNotThrow(() -> result.block());
        verify(likeRepository, times(1)).delete(any(LikeDatabaseEntity.class));

    }

    @Test
    void findByVideoId_shouldThrowExceptionWhenLikeNotFound() {
        String videoId = "invalid-video-id";

        when(likeRepository.findByVideoId(videoId)).thenReturn(
                Mono.error(new LikeNotFoundException()));

        assertThrows(LikeNotFoundException.class, () -> likeGateway.findByVideoId(videoId).block());

        verify(likeRepository, never()).save(new LikeDatabaseEntity());
    }

    @Test
    void deleteLike_shouldThrowExceptionWhenLikeNotFound() {
        String videoId = "invalid-video-id";

        when(likeRepository.findByVideoId(videoId)).thenReturn(
                Mono.error(new LikeNotFoundException()));

        assertThrows(LikeNotFoundException.class, () -> likeGateway.deleteLike(videoId).block());

        verify(likeRepository, never()).delete(new LikeDatabaseEntity());
    }

}
