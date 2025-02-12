package com.self.project.movieapi.Theater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterControlller {

    private final TheaterService theaterService;
    @Autowired
    public TheaterControlller(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping()
    public List<TheaterModel> getTheaters() {
        return theaterService.getTheaters();
    }

    @GetMapping("/{id}/showtime")
    public String getShowtime(@PathVariable String id) {
        return theaterService.getShowtimeOfTheater(id).toString();
    }
}
