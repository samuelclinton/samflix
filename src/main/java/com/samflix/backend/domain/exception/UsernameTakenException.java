package com.samflix.backend.domain.exception;

public class UsernameTakenException extends BusinessLogicException {

    public UsernameTakenException(String username) {
        super("Já existe um usuário cadastrado com o nome de usuário " + username);
    }

}
