package com.samflix.backend.api.controller.user;

import com.samflix.backend.api.controller.model.LikeDto;
import com.samflix.backend.api.controller.model.UsernameDto;
import com.samflix.backend.domain.model.User;
import com.samflix.backend.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1Impl implements UserControllerV1 {

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    private final UserService userService;

    @Override
    @PostMapping(consumes = JSON, produces = JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody @Valid UsernameDto usernameDto) {
        return userService.create(usernameDto);
    }

    @Override
    @PutMapping(value = "/{userId}", consumes = JSON, produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> update(@PathVariable String userId,
                             @RequestBody @Valid UsernameDto usernameDto) {
        return userService.update(userId, usernameDto);
    }

    @Override
    @GetMapping(value = "/{userId}", produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable String userId) {
        return userService.get(userId);
    }

    @Override
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String userId) {
        return userService.delete(userId);
    }

    @Override
    @PutMapping(value = "/{userId}/likes", consumes = JSON, produces = JSON)
    @ResponseStatus(HttpStatus.OK)
    public void likeVideo(@PathVariable String userId, @RequestBody @Valid LikeDto likeDto) {
        userService.like(userId, likeDto);
    }

    @Override
    @DeleteMapping("/{userId}/likes/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislikeVideo(@PathVariable String userId, @PathVariable String videoId) {
        userService.dislike(userId, videoId);
    }

    public UserControllerV1Impl(UserService userService) {
        this.userService = userService;
    }

}
