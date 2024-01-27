package com.samflix.backend.domain.exception;

public class UserDidNotLikeVideoException extends BusinessLogicException {

    public UserDidNotLikeVideoException(String videoId) {
        super("Você não curtiu o vídeo de ID " + videoId);
    }

}
