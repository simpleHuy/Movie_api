package com.self.project.movieapi.Seat;

import com.self.project.movieapi.Seat.model.SeatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatModel, String> {
}
