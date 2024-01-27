package com.samflix.backend.application.usecases;

import com.samflix.backend.domain.entities.Like;
import com.samflix.backend.domain.exception.UserAlreadyLikedVideoException;
import com.samflix.backend.domain.exception.UserDidNotLikeVideoException;
import com.samflix.backend.infrastructure.gateways.LikeGateway;
import com.samflix.backend.infrastructure.gateways.VideoGateway;
import reactor.core.publisher.Mono;

public class VideoLikeAndDislikeInteractor implements VideoLikeAndDislikeUseCase {

    private final VideoGateway videoGateway;
    private final LikeGateway likeGateway;

    public VideoLikeAndDislikeInteractor(VideoGateway videoGateway, LikeGateway likeGateway) {
        this.videoGateway = videoGateway;
        this.likeGateway = likeGateway;
    }

    @Override
    public Mono<Void> like(String videoId) {
        return videoGateway.findById(videoId)
                .flatMap(video -> {
                    if (Boolean.TRUE.equals(video.getLiked())) {
                        return Mono.error(new UserAlreadyLikedVideoException(videoId));
                    } else {
                        video.setLiked(true);
                        return videoGateway.saveVideo(video)
                                .flatMap(savedVideo -> likeGateway.saveLike(
                                        new Like(savedVideo.getId(), savedVideo.getCategory())));
                    }
                });
    }

    @Override
    public Mono<Void> dislike(String videoId) {
        return videoGateway.findById(videoId)
                .flatMap(video -> {
                    if (Boolean.FALSE.equals(video.getLiked())) {
                        return Mono.error(new UserDidNotLikeVideoException(videoId));
                    } else {
                        video.setLiked(false);
                        return videoGateway.saveVideo(video)
                                .flatMap(savedVideo -> likeGateway.deleteLike(savedVideo.getId()));
                    }
                });
    }

}
