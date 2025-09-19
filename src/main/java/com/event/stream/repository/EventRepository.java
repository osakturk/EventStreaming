package com.event.stream.repository;

import com.event.stream.model.Event;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends MongoRepository<Event, UUID> {
    @NotNull Page<Event> findAll(@NotNull Pageable pageable);
    List<Event> findEventByUserId(Long userId);
    List<Event> findEventByPlatform(String platform);
    List<Event> findEventByUserIdAndPlatform(Long userId, String platform);
}
