package com.self.project.movieapi.Showtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    @Autowired
    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public ShowtimeModel getShowtime(String id) {
        return showtimeRepository.findById(id).orElse(null);
    }
}
