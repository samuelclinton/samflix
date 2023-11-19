package com.samflix.backend.api.controller.user;

import com.samflix.backend.domain.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1 implements UserController {

    @Override
    @PostMapping
    public User create() {
        return null;
    }

    @Override
    @PutMapping("/{userId}")
    public User update(@PathVariable String userId) {
        return null;
    }

    @Override
    @GetMapping("/{userId}")
    public User get(@PathVariable String userId) {
        return null;
    }

    @Override
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId) {

    }

    @Override
    @PutMapping("/{userId}/likes")
    public void likeVideo(@PathVariable String userId) {

    }

    @Override
    @DeleteMapping("/{userId}/likes/{videoId}")
    public void dislikeVideo(@PathVariable String userId, @PathVariable String videoId) {

    }

}
