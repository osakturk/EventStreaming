package com.event.stream.controller;


import com.event.stream.model.Show;
import com.event.stream.service.ShowService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<Show> getShows(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return showService.getShows(page, size);
    }

    @GetMapping("/count/released-after")
    public long getCountOfShowsReleasedAfter(
            @RequestParam(defaultValue = "2020") int year
    ) {
        return showService.countShowsReleasedInOrAfter(year);
    }


    @GetMapping("/casts/{id}")
    public List<String> getFirstCastOfTheShowByShowId(@PathVariable("id") String id){
        return showService.getCastMembersById(id);
    }
}
