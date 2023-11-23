package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static com.samflix.backend.utils.UserHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureDataMongo
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll().block();
    }

    @Test
    void shouldSaveUser() {
        var user = saveUser(generateUser());

        assertThat(user)
                .isNotNull()
                .isInstanceOf(User.class);

        assertThat(user.getId())
                .isEqualTo(USER_ID);

        assertThat(user.getUsername())
                .isEqualTo(USERNAME);
    }

    @Test
    void shouldGetUser() {
        saveUser(generateUser());

        var foundUser = userRepository.findById(USER_ID).block();

        assertThat(foundUser)
                .isNotNull()
                .isInstanceOf(User.class);

        assertThat(foundUser.getId())
                .isEqualTo(USER_ID);

        assertThat(foundUser.getUsername())
                .isEqualTo(USERNAME);
    }

    @Test
    void shouldUpdateUsername() {
        var user = saveUser(generateUser());

        assertThat(user.getUsername())
                .isEqualTo(USERNAME);

        user.setUsername(UPDATED_USERNAME);

        var updatedUser = saveUser(user);

        assertThat(updatedUser.getUsername())
                .isNotNull()
                .isEqualTo(UPDATED_USERNAME);
    }

    @Test
    void shouldDeleteUser() {
        var user = saveUser(generateUser());

        assertThat(user.getId())
                .isNotNull()
                .isEqualTo(USER_ID);

        userRepository.deleteById(USER_ID).block();

        var deletedUser = userRepository.findById(USER_ID).block();

        assertThat(deletedUser).isNull();
    }

    private User saveUser(User user) {
        Mono<User> save = userRepository.save(user);
        Mono<User> find = userRepository.findById(user.getId());
        Mono<User> mono = Mono.from(save).then(find);
        return mono.block();
    }

}
