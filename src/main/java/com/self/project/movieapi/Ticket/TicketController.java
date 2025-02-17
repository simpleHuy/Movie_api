package com.self.project.movieapi.Ticket;

import com.self.project.movieapi.Ticket.model.TicketModel;
import com.self.project.movieapi.Ticket.model.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/ticket")
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public void addTickets(@RequestBody TicketRequest ticket) {
        /*
         * request body example:
         * {
         *   "seat": [{"code": "A1", "seatTypeId": 1}, {"code": "A2", "seatTypeId": 1}],
         *   "userId": 1,
         *   "showtimeId": 1
         * }
         */
        ticketService.addTickets(ticket);
    }
}
