package com.event.stream.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class TimeZoneResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("zones")
    private List<TimezoneInfo> zones;

    // Constructors, getters, and setters (if needed) go here


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TimezoneInfo> getZones() {
        return zones;
    }

    public void setZones(List<TimezoneInfo> zones) {
        this.zones = zones;
    }

    @Override
    public String toString() {
        return "TimeZoneResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", zones=" + zones.toString() +
                '}';
    }
}

