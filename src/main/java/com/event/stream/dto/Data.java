package com.event.stream.dto;

import com.event.stream.model.Show;
import com.event.stream.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JsonProperty("show")
    private Show show;

    @JsonProperty("event_date")
    private String eventDate;

    @JsonProperty("user")
    private User user;


    public Data() {
    }

    public Data(Show show, String eventDate, User user) {
        this.show = show;
        this.eventDate = eventDate;
        this.user = user;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Data{" +
                "show=" + show +
                ", eventDate='" + eventDate + '\'' +
                ", user=" + user +
                '}';
    }
}
