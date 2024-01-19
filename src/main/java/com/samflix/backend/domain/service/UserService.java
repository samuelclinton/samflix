package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.UsernameDto;
import com.samflix.backend.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> get(String userId);
    Mono<User> create(UsernameDto usernameDto);
    Mono<User> update(String userId, UsernameDto usernameDto);
    Mono<Void> delete(String userId);

}
