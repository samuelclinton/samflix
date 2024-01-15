package com.samflix.backend.domain.exception;

public class SwitchingToSameUsernameException extends BusinessLogicException {

    public SwitchingToSameUsernameException() {
        super("O novo nome de usuário deve ser diferente do antigo");
    }

}
