package com.samflix.backend.api.controller.user;

import com.samflix.backend.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1Impl implements UserControllerV1 {

    @Override
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public User create() {
        return null;
    }

    @Override
    @PutMapping(
            value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable String userId) {
        return null;
    }

    @Override
    @GetMapping(
            value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable String userId) {
        return null;
    }

    @Override
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String userId) {

    }

    @Override
    @PutMapping(
            value = "/{userId}/likes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public void likeVideo(@PathVariable String userId) {

    }

    @Override
    @DeleteMapping("/{userId}/likes/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislikeVideo(@PathVariable String userId, @PathVariable String videoId) {

    }

}
