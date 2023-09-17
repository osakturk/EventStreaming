package com.event.stream.controller;

import com.event.stream.model.Event;
import com.event.stream.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/users/{userId}")
    public List<Event> getEventsByUserId(@PathVariable("userId") Long userId) {
        return eventService.getEventsByUserId(userId);
    }

    @GetMapping("/platforms/{platform}")
    public List<Event> getEventsByPlatform(@PathVariable("platform") String platform){
        return eventService.getEventsByPlatform(platform);
    }

    @GetMapping("/statics/{userId}/{platform}")
    public Double getStaticsByUserIdAndPlatform(@PathVariable("userId") Long userId, @PathVariable("platform") String platform) {
        return eventService.calculateStaticsByUserIdAndPlatform(userId, platform);
    }
}
