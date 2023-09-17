package com.event.stream.dto;

import com.event.stream.model.Event;
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

@Component
public class EventParser {
    private static final Logger logger = LoggerFactory.getLogger(EventParser.class);

    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final ShowService showService;
    private final EventService eventService;

    public EventParser(ObjectMapper objectMapper, UserService userService, ShowService showService, EventService eventService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.showService = showService;
        this.eventService = eventService;
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
            eventService.addEvent(new Event(eventDto.getId(), eventDto.getData().getUser().getId(), eventDto.getEvent(), data.getShow().getShowId(), data.getShow().getPlatform(), data.getEventDate()));
        } catch (IOException e) {
            logger.error("Failed to parse JSON", e);
        }
        return eventDto;
    }
}


