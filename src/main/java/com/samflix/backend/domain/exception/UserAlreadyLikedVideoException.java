package com.samflix.backend.domain.exception;

public class UserAlreadyLikedVideoException extends BusinessLogicException {

    public UserAlreadyLikedVideoException(String videoId) {
        super("O usuário já curtiu o vídeo " + videoId);
    }

}
