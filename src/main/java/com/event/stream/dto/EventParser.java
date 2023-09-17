package com.event.stream.dto;

import com.event.stream.model.Event;
import com.event.stream.model.TimezoneInfo;
import com.event.stream.service.TimezoneService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.event.stream.service.EventService;
import com.event.stream.service.ShowService;
import com.event.stream.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class EventParser {
    private static final Logger logger = LoggerFactory.getLogger(EventParser.class);

    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final ShowService showService;
    private final EventService eventService;
    private final TimezoneService timezoneService;

    public EventParser(ObjectMapper objectMapper, UserService userService, ShowService showService, EventService eventService, TimezoneService timezoneService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.showService = showService;
        this.eventService = eventService;
        this.timezoneService = timezoneService;
    }

    //I should have created this method because id and event are not inside the json object
    public StreamingResponseDTO parseJsonFromByteArray(byte[] byteArray) {
        StreamingResponseDTO eventDto = new StreamingResponseDTO();
        try {
            String jsonString = new String(byteArray, StandardCharsets.UTF_8);
            String[] lines = jsonString.split("\\n");

            String id = null;
            String event = null;

            // Iterate through lines to find id and event
            for (String line : lines) {
                if (line.startsWith("id:")) {
                    id = line.substring(3).trim();
                } else if (line.startsWith("event:")) {
                    event = line.substring(6).trim();
                }
            }

            // Set id and event values in the DTO
            if (id != null) {
                eventDto.setId(id);
            }

            if (event != null) {
                eventDto.setEvent(event);
            }

            String jsonPart = jsonString.replaceFirst("id:.+?\\n", "").replaceFirst("event:.+?\\n", "").replaceFirst("data:","");
            JsonNode dataNode = objectMapper.readTree(jsonPart);
            Data data = objectMapper.treeToValue(dataNode, Data.class);
            eventDto.setData(data);
            userService.addUser(data.getUser());
            showService.addShow(data.getShow());
            Instant eventInstant = parseDateWithCountryCode(data.getEventDate(), data.getUser().getCountry());

            eventService.addEvent(new Event(eventDto.getId(), eventDto.getData().getUser().getId(), eventDto.getEvent(), data.getShow().getShowId(), data.getShow().getPlatform(), eventInstant));
        } catch (IOException e) {
            logger.error("Failed to parse JSON", e);
        }
        return eventDto;
    }

    public Instant parseDateWithCountryCode(String dateStr, String countryCode) throws IOException {
        // Get the time zone ID for the specified country code
        List<TimezoneInfo> timeZoneInfo = timezoneService.getTimezoneByCountryCode(countryCode);

        if (timeZoneInfo != null) {
            String pattern = "dd-MM-yyyy HH:mm:ss.SSS";
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);

            // Convert the ZonedDateTime to Instant using the retrieved time zone
            ZoneId zoneId = ZoneId.of(timeZoneInfo.get(0).getZoneName());
            return localDateTime.atZone(zoneId).toInstant();
        } else {
            // Handle the case when no time zone is found for the country code
            throw new IllegalArgumentException("Invalid country code: " + countryCode);
        }
    }
}


