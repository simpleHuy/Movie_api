package com.self.project.movieapi.Theater;

import com.self.project.movieapi.Movie.MovieModel;
import com.self.project.movieapi.Showtime.ShowtimeModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "theater")
public class TheaterModel {
    @Id
    private String id;
    private String name;
    private String location;
    private String city;
    private String phone;
    private String imageUrl;

    // relative columns
    @OneToMany(mappedBy = "theater")
    private List<ShowtimeModel> showtimes;
}
