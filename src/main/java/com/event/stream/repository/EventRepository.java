package com.event.stream.repository;

import com.event.stream.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends MongoRepository<Event, UUID> {

    List<Event> findEventByUserId(Long userId);
    List<Event> findEventByPlatform(String platform);
    List<Event> findEventByUserIdAndPlatform(Long userId, String platform);
}
