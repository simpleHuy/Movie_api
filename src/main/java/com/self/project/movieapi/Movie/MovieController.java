package com.self.project.movieapi.Movie;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieModel> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public MovieModel getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }
}
