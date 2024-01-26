package com.samflix.backend.domain.service;

public interface LikeService {

    void like(String videoId);
    void dislike(String videoId);

}
