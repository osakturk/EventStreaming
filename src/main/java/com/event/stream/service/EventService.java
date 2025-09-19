package com.event.stream.service;

import com.event.stream.model.Event;
import com.event.stream.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public Page<Event> getEvents(int page, int size) {
        return eventRepository.findAll(PageRequest.of(page, size));
    }

    public List<Event> getEventsByUserId(Long userId) {
        return eventRepository.findEventByUserId(userId);
    }

    public List<Event> getEventsByPlatform(String platform) {
        return eventRepository.findEventByPlatform(platform);
    }

    public Double calculateStaticsByUserIdAndPlatform(Long userId, String platform) {
        List<Event> events = eventRepository.findEventByUserIdAndPlatform(userId, platform);

        long totalEvents = events.size();
        long successfulStreamEvents = 0;

        for (int i = 0; i < events.size() - 1; i++) {
            Event currentEvent = events.get(i);
            Event nextEvent = events.get(i + 1);

            if (currentEvent.getEventDescription().equals("stream-started") &&
                    nextEvent.getEventDescription().equals("stream-finished") &&
                    currentEvent.getShowId().equals(nextEvent.getShowId()) &&
                    currentEvent.getPlatform().equals(nextEvent.getPlatform())) {
                successfulStreamEvents++;
            }
        }
        return (double) successfulStreamEvents / totalEvents * 100.0;

    }
}
