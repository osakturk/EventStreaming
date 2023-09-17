package com.event.stream.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class StreamingResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("event")
    private String event;

    @JsonProperty("data")
    private Data data;

    public StreamingResponseDTO() {
    }

    public StreamingResponseDTO(String id, String event, Data data) {
        this.id = id;
        this.event = event;
        this.data = data;
    }

    // Getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StreamingResponseDTO{" +
                "id='" + id + '\'' +
                ", event='" + event + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StreamingResponseDTO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEvent(), that.getEvent()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEvent(), getData());
    }
}
