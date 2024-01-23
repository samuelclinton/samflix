package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.LikeDto;
import com.samflix.backend.domain.exception.LikeNotFoundException;
import com.samflix.backend.domain.exception.UserAlreadyLikedVideoException;
import com.samflix.backend.domain.model.Like;
import com.samflix.backend.domain.model.User;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.LikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class LikeServiceImpl implements LikeService {

    private final UserService userService;
    private final VideoService videoService;
    private final LikeRepository likeRepository;

    public Like get(String userId, String videoId) {
        return likeRepository.findByUserIdAndVideoId(userId, videoId)
                .switchIfEmpty(Mono.error(new LikeNotFoundException()))
                .block();
    }

    @Override
    public void add(String userId, LikeDto likeDto) {

        Optional<Like> existingLike = likeRepository
                .findByUserIdAndVideoId(userId, likeDto.getVideoId())
                .blockOptional();

        if (existingLike.isPresent()) {
            throw new UserAlreadyLikedVideoException(likeDto.getVideoId());
        }

        User user = userService.get(userId);
        Video video = videoService.get(likeDto.getVideoId());

        if (user != null && video != null) {
            Like like = Like.builder()
                    .userId(user.getId())
                    .videoId(video.getId())
                    .videoCategory(video.getCategory())
                    .build();

            videoService.addLike(video);
            likeRepository.save(like).block();
        }
    }

    @Override
    public void remove(String userId, String videoId) {
        Like like = get(userId, videoId);
        Video video = videoService.get(videoId);
        videoService.removeLike(video);
        likeRepository.delete(like).block();
    }

    public LikeServiceImpl(LikeRepository likeRepository, UserService userService, VideoService videoService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.videoService = videoService;
    }

}
