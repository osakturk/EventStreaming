package com.event.stream.controller;

import com.event.stream.model.TimezoneInfo;
import com.event.stream.service.TimezoneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/timezones")
public class TimezoneController {

    private final TimezoneService timezoneService;

    public TimezoneController(TimezoneService timezoneService) {
        this.timezoneService = timezoneService;
    }

    @GetMapping
    public List<TimezoneInfo> getTimezonesAndSaveToDatabase() throws IOException {
        return timezoneService.retrieveAndSaveTimezones();
    }
}
