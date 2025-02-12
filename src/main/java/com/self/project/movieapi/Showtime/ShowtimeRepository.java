package com.self.project.movieapi.Showtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowtimeModel, String> {
    @Query("SELECT s FROM showtime s WHERE s.theater.id = ?1")
    public List<ShowtimeModel> findByTheaterId(String theaterId);
}
