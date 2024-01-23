package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.LikeDto;

public interface LikeService {

    void add(String userId, LikeDto likeDto);
    void remove(String userId, String videoId);

}
