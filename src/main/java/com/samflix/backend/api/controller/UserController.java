package com.samflix.backend.api.controller;

import com.samflix.backend.domain.model.User;

public interface UserController {

    User create();
    User update(String userId);
    User get(String userId);
    void delete(String userId);
    void likeVideo(String userId, String videoId);
    void dislikeVideo(String userId, String videoId);

}
