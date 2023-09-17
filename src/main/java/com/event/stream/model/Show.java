package com.event.stream.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Show {
    @Id
    private String id;
    @JsonProperty("show_id")
    private String showId;

    @JsonProperty("cast")
    private String cast;

    @JsonProperty("country")
    private String country;

    @JsonProperty("date_added")
    private String dateAdded;

    @JsonProperty("description")
    private String description;

    @JsonProperty("director")
    private String director;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("listed_in")
    private String listedIn;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("release_year")
    private int releaseYear;

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private String type;

    @JsonProperty("platform")
    private String platform;

    public Show() {
    }

    public Show(String id, String showId, String cast, String country, String dateAdded, String description,
                String director, String duration, String listedIn, String rating, int releaseYear, String title,
                String type, String platform) {
        this.id = id;
        this.showId = showId;
        this.cast = cast;
        this.country = country;
        this.dateAdded = dateAdded;
        this.description = description;
        this.director = director;
        this.duration = duration;
        this.listedIn = listedIn;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.title = title;
        this.type = type;
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getListedIn() {
        return listedIn;
    }

    public void setListedIn(String listedIn) {
        this.listedIn = listedIn;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                "showId='" + showId + '\'' +
                ", cast='" + cast + '\'' +
                ", country='" + country + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", duration='" + duration + '\'' +
                ", listedIn='" + listedIn + '\'' +
                ", rating='" + rating + '\'' +
                ", releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
