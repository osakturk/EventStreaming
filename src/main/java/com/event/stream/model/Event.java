package com.event.stream.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Event {
    @Id
    private String id;
    private String eventId;
    private Long userId;
    private String eventDescription;
    private String showId;
    private String platform;
    private String eventDate;

    public Event() {
    }

    public Event(String eventId, Long userId, String eventDescription, String showId, String platform, String eventDate) {
        this.id = UUID.randomUUID().toString();
        this.eventId = eventId;
        this.userId = userId;
        this.eventDescription = eventDescription;
        this.showId = showId;
        this.platform = platform;
        this.eventDate = eventDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                "eventId='" + eventId + '\'' +
                ", userId=" + userId +
                ", eventDescription='" + eventDescription + '\'' +
                ", showId='" + showId + '\'' +
                ", platform='" + platform + '\'' +
                ", eventDate='" + eventDate + '\'' +
                '}';
    }
}
