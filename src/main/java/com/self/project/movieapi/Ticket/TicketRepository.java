package com.self.project.movieapi.Ticket;

import com.self.project.movieapi.Ticket.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, String> {
}
