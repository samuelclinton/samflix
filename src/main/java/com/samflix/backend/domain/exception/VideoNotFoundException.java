package com.samflix.backend.domain.exception;

public class VideoNotFoundException extends EntityNotFoundException {

    public VideoNotFoundException(String id) {
        super("Nenhum vídeo encontrado com o ID " + id);
    }

}
