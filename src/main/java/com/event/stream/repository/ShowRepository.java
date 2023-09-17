package com.event.stream.repository;

import com.event.stream.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ShowRepository extends MongoRepository<Show, UUID> {

    Show findShowById(String id);
}
