package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.LikeDto;
import com.samflix.backend.api.controller.model.UsernameDto;
import com.samflix.backend.domain.exception.*;
import com.samflix.backend.domain.model.Like;
import com.samflix.backend.domain.model.User;
import com.samflix.backend.domain.model.Video;
import com.samflix.backend.domain.repository.LikeRepository;
import com.samflix.backend.domain.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final VideoService videoService;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Override
    public User get(String userId) {
        return userRepository
                .findById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)))
                .block();
    }

    private Mono<User> getReactive(String userId) {
        return userRepository
                .findById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)));
    }

    @Override
    @Transactional
    public Mono<User> create(UsernameDto usernameDto) {
        User user = User.builder()
                .username(usernameDto.getUsername())
                .build();
        return userRepository.findByUsername(usernameDto.getUsername())
                .flatMap(existingUser ->
                        Mono.error(new UsernameTakenException(usernameDto.getUsername())))
                .switchIfEmpty(userRepository.save(user))
                .cast(User.class);
    }

    @Override
    @Transactional
    public Mono<User> update(String userId, UsernameDto usernameDto) {
        return getReactive(userId)
                .flatMap(user -> {
                    var currentUsername = user.getUsername();
                    var newUsername = usernameDto.getUsername();
                    if (currentUsername.equals(newUsername)) {
                        return Mono.error(new SwitchingToSameUsernameException());
                    }
                    user.setUsername(newUsername);
                    return userRepository.findByUsername(newUsername)
                            .flatMap(existingUser -> Mono.error(new UsernameTakenException(newUsername)))
                            .switchIfEmpty(userRepository.save(user))
                            .cast(User.class);
                });
    }

    @Override
    public Mono<Void> delete(String userId) {
        User user = get(userId);
        deleteAllLikesByUser(user);
        videoService.deleteAllVideosByUser(userId);
        return getReactive(userId)
                .flatMap(userRepository::delete);
    }

    public Like getLike(String userId, String videoId) {
        return likeRepository.findByUserIdAndVideoId(userId, videoId)
                .switchIfEmpty(Mono.error(new LikeNotFoundException()))
                .block();
    }

    @Override
    public void like(String userId, LikeDto likeDto) {

        Optional<Like> existingLike = likeRepository
                .findByUserIdAndVideoId(userId, likeDto.getVideoId())
                .blockOptional();

        if (existingLike.isPresent()) {
            throw new UserAlreadyLikedVideoException(likeDto.getVideoId());
        }

        User user = get(userId);
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
    public void dislike(String userId, String videoId) {
        Like like = getLike(userId, videoId);
        Video video = videoService.get(videoId);
        videoService.removeLike(video);
        likeRepository.delete(like).block();
    }

    @Override
    public void deleteAllLikesByUser(User user) {
        likeRepository.findAllByUserId(user.getId())
                .collectList()
                .flatMapMany(likes -> {
                    Flux<Like> likeFlux = Flux.fromIterable(likes);
                    return likeRepository.deleteAll(likeFlux);
                })
                .subscribe();
    }

    public UserServiceImpl(@Lazy VideoService videoService, UserRepository userRepository, LikeRepository likeRepository) {
        this.videoService = videoService;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

}
