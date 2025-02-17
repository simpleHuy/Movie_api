package com.self.project.movieapi.Seat;

import com.self.project.movieapi.Seat.model.SeatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void addSeat (SeatModel seat) {
        seatRepository.save(seat);
    }
}
