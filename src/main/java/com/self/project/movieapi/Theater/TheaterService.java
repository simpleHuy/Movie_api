package com.self.project.movieapi.Theater;

import com.self.project.movieapi.Showtime.ShowtimeModel;
import com.self.project.movieapi.Showtime.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository, ShowtimeRepository showtimeRepository) {
        this.theaterRepository = theaterRepository;
        this.showtimeRepository = showtimeRepository;
    }

    public List<TheaterModel> getTheaters() {
        return theaterRepository.findAll();
    }

    public TheaterModel getShowtimeOfTheater(String id) {
        TheaterModel theater = theaterRepository.findById(id).orElse(null);
        if(theater == null) return null;

        List<ShowtimeModel> showtime = showtimeRepository.findByTheaterId(id);
        theater.setShowtime(showtime);
        return theater;
    }
}
