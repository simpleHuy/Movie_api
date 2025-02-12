package com.self.project.movieapi.Ticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
public class SeatTypeModel {
    @Id
    private String id;
    private String type;
    private Double price;
}
