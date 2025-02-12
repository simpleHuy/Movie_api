package com.self.project.movieapi.Movie;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieModel> getMovies() {
        return movieRepository.findAll();
    }

    public MovieModel getMovieById(String id) {
        return movieRepository.findById(Long.parseLong(id)).orElse(null);
    }
}
