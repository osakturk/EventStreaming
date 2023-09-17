package com.event.stream.service;

import com.event.stream.model.Event;
import com.event.stream.repository.EventRepository;
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

    public List<Event> getEvents() {
        return eventRepository.findAll();
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
            for (int j = 0; j < events.size() - 1; j++) {
                Event nextEvent = events.get(j);
                if (nextEvent.equals(currentEvent)) {
                    continue;
                }
                if (currentEvent.getEventDescription().equals("stream-started") &&
                        nextEvent.getEventDescription().equals("stream-ended") &&
                        currentEvent.getShowId().equals(nextEvent.getShowId())) {
                    successfulStreamEvents++;
                }
            }

        }
        return (double) successfulStreamEvents / totalEvents * 100.0;

    }
}
