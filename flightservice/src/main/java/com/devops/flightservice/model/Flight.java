package com.devops.flightservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Flight.
 */
@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}\\d{4}$", message = "Flight number must be in format AA1234")
    @Column(unique = true)
    private String flightNumber;

    @NotBlank
    @Size(min = 3, max = 3)
    private String origin;

    @NotBlank
    @Size(min = 3, max = 3)
    private String destination;

    @NotNull
    private Integer totalSeats;

    @NotNull
    private Integer availableSeats;

    @NotBlank
    private String status;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightSchedule> schedules = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void validateSeats() {
        if (availableSeats > totalSeats) {
            throw new IllegalStateException("Available seats cannot exceed total seats");
        }
        if (availableSeats < 0) {
            throw new IllegalStateException("Available seats cannot be negative");
        }
    }
}
