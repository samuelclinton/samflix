package com.samflix.backend.domain.service;

import com.samflix.backend.domain.exception.LikeNotFoundException;
import com.samflix.backend.domain.exception.UserAlreadyLikedVideoException;
import com.samflix.backend.domain.model.Like;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.LikeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final VideoService videoService;
    private final LikeRepository likeRepository;

    public Like getLike(String videoId) {
        return likeRepository.findByVideoId(videoId)
                .switchIfEmpty(Mono.error(new LikeNotFoundException()))
                .block();
    }

    @Override
    public void like(String videoId) {

        Optional<Like> existingLike = likeRepository
                .findByVideoId(videoId)
                .blockOptional();

        if (existingLike.isPresent()) {
            throw new UserAlreadyLikedVideoException(videoId);
        }

        Video video = videoService.get(videoId);

        if (video != null) {
            Like like = Like.builder()
                    .videoId(video.getId())
                    .videoCategory(video.getCategory())
                    .build();
            videoService.addLike(video);
            likeRepository.save(like).block();
        }
    }

    @Override
    public void dislike(String videoId) {
        Like like = getLike(videoId);
        Video video = videoService.get(videoId);
        videoService.removeLike(video);
        likeRepository.delete(like).block();
    }

    public LikeServiceImpl(@Lazy VideoService videoService, LikeRepository likeRepository) {
        this.videoService = videoService;
        this.likeRepository = likeRepository;
    }

}
