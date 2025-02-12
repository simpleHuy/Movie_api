package com.self.project.movieapi.Showtime;

import com.self.project.movieapi.Movie.MovieModel;
import com.self.project.movieapi.Theater.TheaterModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "showtime")
public class ShowtimeModel {
    @Id
    private String id;
    private String code;
    private Time screeningTime;
    private Date screeningDate;

    // relative columns
    @ManyToOne
    private MovieModel movie;
    @ManyToOne
    private TheaterModel theater;
}
