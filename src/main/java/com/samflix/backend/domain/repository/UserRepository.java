package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
