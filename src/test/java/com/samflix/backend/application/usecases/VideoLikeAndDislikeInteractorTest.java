package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Like;
import com.samflix.backend.domain.entities.Video;
import com.samflix.backend.domain.exception.UserAlreadyLikedVideoException;
import com.samflix.backend.domain.exception.UserDidNotLikeVideoException;
import com.samflix.backend.infrastructure.gateways.LikeGateway;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.VideoHelper.VIDEO_ID;
import static com.samflix.backend.utils.VideoHelper.generateVideoDatabaseEntity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoLikeAndDislikeInteractorTest {

    @Mock
    private VideoGateway videoGateway;

    @Mock
    private LikeGateway likeGateway;

    @InjectMocks
    private VideoLikeAndDislikeInteractor interactor;

    @Test
    void like_shouldLikeVideo() {
        Video video = new Video(generateVideoDatabaseEntity());

        when(videoGateway.findById(VIDEO_ID)).thenReturn(Mono.just(video));
        when(videoGateway.saveVideo(video)).thenReturn(Mono.just(video));
        when(likeGateway.saveLike(new Like(VIDEO_ID, video.getCategory()))).thenReturn(Mono.empty());

        interactor.like(VIDEO_ID).block();

        verify(videoGateway, times(1)).saveVideo(video);
        verify(likeGateway, times(1)).saveLike(new Like(VIDEO_ID, video.getCategory()));
    }

    @Test
    void dislike_shouldDislikeVideo() {
        Video video = new Video(generateVideoDatabaseEntity());
        video.setLiked(true);

        when(videoGateway.findById(VIDEO_ID)).thenReturn(Mono.just(video));
        when(videoGateway.saveVideo(video)).thenReturn(Mono.just(video));
        when(likeGateway.deleteLike(VIDEO_ID)).thenReturn(Mono.empty());

        interactor.dislike(VIDEO_ID).block();

        verify(videoGateway, times(1)).saveVideo(video);
        verify(likeGateway, times(1)).deleteLike(VIDEO_ID);
    }

    @Test
    void like_shouldThrowExceptionWhenAlreadyLiked() {
        Video video = new Video(generateVideoDatabaseEntity());

        when(videoGateway.findById(VIDEO_ID))
                .thenReturn(Mono.error(new UserAlreadyLikedVideoException(VIDEO_ID)));

        assertThrows(UserAlreadyLikedVideoException.class, () -> interactor.like(VIDEO_ID).block());

        verify(videoGateway, never()).saveVideo(video);
        verify(likeGateway, never()).saveLike(new Like(VIDEO_ID, video.getCategory()));
    }

    @Test
    void dislike_shouldThrowExceptionWhenAlreadyDisliked() {
        Video video = new Video(generateVideoDatabaseEntity());

        when(videoGateway.findById(VIDEO_ID)).thenReturn(Mono.just(video));

        assertThrows(UserDidNotLikeVideoException.class, () -> interactor.dislike(VIDEO_ID).block());

        verify(videoGateway, never()).saveVideo(video);
        verify(likeGateway, never()).deleteLike(VIDEO_ID);
    }
}
