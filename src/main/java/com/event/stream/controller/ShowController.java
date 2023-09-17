package com.event.stream.controller;


import com.event.stream.model.Show;
import com.event.stream.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public List<Show> getShows() {
        return showService.getShows();
    }


    @GetMapping("/casts/{id}")
    public List<String> getFirstCastOfTheShowByShowId(@PathVariable("id") String id){
        return showService.getCastMembersById(id);
    }
}
