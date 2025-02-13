package com.self.project.movieapi.Ticket.model;

import com.self.project.movieapi.Seat.model.SeatRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {
    private List<SeatRequest> seat; // seat code
    private String showtimeId;
    private String userId;
}
