package com.self.project.movieapi.Showtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    @Autowired
    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping("{id}")
    public ShowtimeModel getShowtime(@PathVariable String id) {
        return showtimeService.getShowtime(id);
    }
}
