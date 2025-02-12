package com.self.project.movieapi.Movie;

import com.self.project.movieapi.Showtime.ShowtimeModel;
import com.self.project.movieapi.Theater.TheaterModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel {
    // absolute columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genres;
    private String director;
    private String actors;
    private Integer time;
    private String description;
    private String image;

    // relative columns
    @OneToMany(mappedBy = "movie")
    private List<ShowtimeModel> showtimes;
}
