package com.samflix.backend.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String id) {
        super("Nenhum usuário encontrado com o ID " + id);
    }

}
