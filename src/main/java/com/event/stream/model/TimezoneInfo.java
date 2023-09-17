package com.event.stream.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TimezoneInfo {

    @Id
    private String id;
    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("countryName")
    private String countryName;

    @JsonProperty("zoneName")
    private String zoneName;

    @JsonProperty("gmtOffset")
    private int gmtOffset;

    @JsonProperty("timestamp")
    private long timestamp;

    public TimezoneInfo() {
    }

    public TimezoneInfo(String id, String countryCode, String countryName, String zoneName, int gmtOffset, long timestamp) {
        this.id = id;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.zoneName = zoneName;
        this.gmtOffset = gmtOffset;
        this.timestamp = timestamp;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public int getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(int gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Constructors, getters, and setters (if needed) go here

    @Override
    public String toString() {
        return "TimeZoneInfo{" +
                "id='" + id + '\'' +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", gmtOffset=" + gmtOffset +
                ", timestamp=" + timestamp +
                '}';
    }
}

