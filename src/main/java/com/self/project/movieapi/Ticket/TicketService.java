package com.self.project.movieapi.Ticket;

import com.self.project.movieapi.Seat.model.SeatModel;
import com.self.project.movieapi.Seat.SeatService;
import com.self.project.movieapi.Seat.model.SeatRequest;
import com.self.project.movieapi.Showtime.ShowtimeService;
import com.self.project.movieapi.Ticket.model.TicketModel;
import com.self.project.movieapi.Ticket.model.TicketRequest;
import com.self.project.movieapi.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final UserService userService;
    private final ShowtimeService showtimeService;
    @Autowired
    public TicketService(TicketRepository ticketRepository, SeatService seatService, UserService userService, ShowtimeService showtimeService) {
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
        this.userService = userService;
        this.showtimeService = showtimeService;
    }

    public TicketModel getTicket(String id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public void addTickets(TicketRequest ticketRequest) {
        // with each code in ticketRequest.code, create a new ticket and save it
        for (SeatRequest seatRequest : ticketRequest.getSeat()) {
            TicketModel ticket = TicketModel.builder()
                    .id(UUID.randomUUID().toString())
                    .showtime(showtimeService.getShowtime(ticketRequest.getShowtimeId()))
                    .user(userService.getUserById(ticketRequest.getUserId()))
                    .build();
            SeatModel seat = SeatModel.builder()
                    .id(UUID.randomUUID().toString())
                    .code(seatRequest.getCode())
                    .seatTypeId(seatRequest.getSeatTypeId())
                    .ticket(ticket)
                    .build();
            seatService.addSeat(seat);
            ticketRepository.save(ticket);
        }
    }

    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }
}
