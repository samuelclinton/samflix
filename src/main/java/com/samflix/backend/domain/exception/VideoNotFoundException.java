package com.samflix.backend.domain.exception;

public class VideoNotFoundException extends EntityNotFoundException {

    public VideoNotFoundException(String message) {
        super(message);
    }

}
