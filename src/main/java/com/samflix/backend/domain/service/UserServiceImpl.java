package com.samflix.backend.domain.service;

import com.samflix.backend.api.controller.model.UsernameDto;
import com.samflix.backend.domain.exception.SwitchingToSameUsernameException;
import com.samflix.backend.domain.exception.UserNotFoundException;
import com.samflix.backend.domain.exception.UsernameTakenException;
import com.samflix.backend.domain.model.User;
import com.samflix.backend.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<User> get(String userId) {
        return userRepository
                .findById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)));
    }

    @Override
    @Transactional
    public Mono<User> create(UsernameDto usernameDto) {
        User user = User.builder()
                .username(usernameDto.getUsername())
                .build();
        return userRepository.findByUsername(usernameDto.getUsername())
                .flatMap(existingUser ->
                        Mono.error(new UsernameTakenException(usernameDto.getUsername())))
                .switchIfEmpty(userRepository.save(user))
                .cast(User.class);
    }

    @Override
    @Transactional
    public Mono<User> update(String userId, UsernameDto usernameDto) {
        return get(userId)
                .flatMap(user -> {
                    var currentUsername = user.getUsername();
                    var newUsername = usernameDto.getUsername();
                    if (currentUsername.equals(newUsername)) {
                        return Mono.error(new SwitchingToSameUsernameException());
                    }
                    user.setUsername(newUsername);
                    return userRepository.findByUsername(newUsername)
                            .flatMap(existingUser -> Mono.error(new UsernameTakenException(newUsername)))
                            .switchIfEmpty(userRepository.save(user))
                            .cast(User.class);
                });
    }

    @Override
    public Mono<Void> delete(String userId) {
        // TODO - Implementar a lógica de excluir os vídeos e likes do usuário
        return get(userId)
                .flatMap(userRepository::delete);
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
