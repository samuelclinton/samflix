package com.samflix.backend.infrastructure.gateways;

import com.samflix.backend.domain.entities.Like;
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
                        Mono.just(new Like(likeDatabaseEntity.getVideoId(), likeDatabaseEntity.getVideoCategory())));
    }

    @Override
    public Mono<Void> saveLike(Like like) {
        return likeRepository.save(new LikeDatabaseEntity(like.videoId(), like.videoCategory()))
                .flatMap(likeDatabaseEntity -> Mono.empty());
    }

    @Override
    public Mono<Void> deleteLike(String videoId) {
        return likeRepository.findByVideoId(videoId)
                .flatMap(likeRepository::delete);
    }

}
