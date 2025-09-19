package com.event.stream.repository;

import com.event.stream.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    @NotNull Page<User> findAll(@NotNull Pageable pageable);
}
