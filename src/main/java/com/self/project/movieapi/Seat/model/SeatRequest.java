package com.self.project.movieapi.Seat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequest {
    private String code;
    private int seatTypeId;
}
