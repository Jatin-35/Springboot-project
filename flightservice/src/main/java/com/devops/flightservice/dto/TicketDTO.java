package com.devops.flightservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Ticket entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    
    @NotNull(message = "Schedule ID is required")
    private Long scheduleId;

    @NotBlank(message = "Passenger name is required")
    private String passengerName;

    @NotBlank(message = "Passenger email is required")
    @Email(message = "Invalid email format")
    private String passengerEmail;

    @NotNull(message = "Seat number is required")
    private Integer seatNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 