package com.event.stream.controller;

import com.event.stream.model.Event;
import com.event.stream.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<Event> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return eventService.getEvents(page, size);
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
