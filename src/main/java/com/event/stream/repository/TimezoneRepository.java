package com.event.stream.repository;

import com.event.stream.model.TimezoneInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimezoneRepository extends MongoRepository<TimezoneInfo, String> {
    List<TimezoneInfo> findTimezoneInfoByCountryCode(String countryCode);
}
