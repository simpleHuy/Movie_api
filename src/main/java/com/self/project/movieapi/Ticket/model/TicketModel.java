package com.self.project.movieapi.Ticket.model;

import com.self.project.movieapi.Showtime.ShowtimeModel;
import com.self.project.movieapi.User.UserModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ticket")
public class TicketModel {
    @Id
    private String id;
    private String code; // seat code

    // relative columns
    @ManyToOne
    private SeatTypeModel seatType;
    @ManyToOne
    private ShowtimeModel showtime;
    @ManyToOne
    private UserModel user;
}
