package com.self.project.movieapi.Movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private Long id;
    private String name;
    private String genres;
    private String director;
    private String actors;
    private Integer time;
    private String description;
    private String image;
}
