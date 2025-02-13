package com.self.project.movieapi.Seat.model;

import com.self.project.movieapi.Ticket.model.TicketModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
public class SeatModel {
    @Id
    private String id;
    private String code;
    private int seatTypeId;

    @ManyToOne
    private TicketModel ticket;
}
