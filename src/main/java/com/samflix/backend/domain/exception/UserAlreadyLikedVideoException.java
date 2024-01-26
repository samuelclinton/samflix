package com.samflix.backend.domain.exception;

public class UserAlreadyLikedVideoException extends BusinessLogicException {

    public UserAlreadyLikedVideoException(String videoId) {
        super("Você já curtiu o vídeo de ID " + videoId);
    }

}
