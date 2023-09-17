package com.event.stream.service;

import com.event.stream.model.Show;
import com.event.stream.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Show addShow(Show event) {
        return showRepository.save(event);
    }

    public List<Show> getShows() {
        return showRepository.findAll();
    }


    public List<String> getCastMembersById(String id) {
        Show show = showRepository.findShowById(id);
        return Arrays.stream(show.getCast().split(", ")).toList();
    }
}
