package com.samflix.backend.domain.exception;

public class LikeNotFoundException extends EntityNotFoundException {

    public LikeNotFoundException() {
        super("Nenhum like encontrado para este vídeo");
    }

}
