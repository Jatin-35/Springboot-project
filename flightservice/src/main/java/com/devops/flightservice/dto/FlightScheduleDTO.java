package com.devops.flightservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for FlightSchedule entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightScheduleDTO {
    private Long id;
    
    @NotNull(message = "Flight ID is required")
    private Long flightId;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
