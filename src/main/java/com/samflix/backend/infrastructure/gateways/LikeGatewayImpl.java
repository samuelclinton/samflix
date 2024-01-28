package com.samflix.backend.infrastructure.gateways;

import com.samflix.backend.domain.entities.Like;
import com.samflix.backend.domain.exception.LikeNotFoundException;
import com.samflix.backend.infrastructure.persistence.LikeRepository;
import com.samflix.backend.infrastructure.persistence.model.LikeDatabaseEntity;
import reactor.core.publisher.Mono;

public class LikeGatewayImpl implements LikeGateway {

    private final LikeRepository likeRepository;

    public LikeGatewayImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Mono<String> getMostLikedCategory() {
        return likeRepository.findMostLikedCategory();
    }

    @Override
    public Mono<Like> findByVideoId(String videoId) {
        return likeRepository.findByVideoId(videoId)
                .flatMap(likeDatabaseEntity ->
                        Mono.just(new Like(likeDatabaseEntity.getVideoId(), likeDatabaseEntity.getVideoCategory())))
                .switchIfEmpty(Mono.error(new LikeNotFoundException()));
    }

    @Override
    public Mono<Void> saveLike(Like like) {
        LikeDatabaseEntity likeDatabaseEntity = new LikeDatabaseEntity(like.videoId(), like.videoCategory());
        return likeRepository.save(likeDatabaseEntity)
                .flatMap(savedLike -> Mono.empty());
    }

    @Override
    public Mono<Void> deleteLike(String videoId) {
        return likeRepository.findByVideoId(videoId)
                .flatMap(likeRepository::delete);
    }

}
