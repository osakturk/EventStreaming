package com.event.stream.repository;

import com.event.stream.model.Show;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ShowRepository extends MongoRepository<Show, UUID> {
    @NotNull Page<Show> findAll(@NotNull Pageable pageable);
    Show findShowById(String id);
    long countByReleaseYearGreaterThanEqual(int year);
}
