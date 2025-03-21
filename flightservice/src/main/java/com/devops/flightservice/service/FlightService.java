package com.devops.flightservice.service;

import com.devops.flightservice.dto.FlightDTO;
import com.devops.flightservice.dto.FlightScheduleDTO;
import com.devops.flightservice.model.FlightStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface for Flight operations.
 * Defines business logic methods for flight management.
 */
public interface FlightService {
    
    /**
     * Create a new flight
     */
    FlightDTO createFlight(FlightDTO flightDTO);

    /**
     * Get flight by ID
     */
    FlightDTO getFlightById(Long id);

    /**
     * Get flight by flight number
     */
    FlightDTO getFlightByNumber(String flightNumber);

    /**
     * Update flight details
     */
    FlightDTO updateFlight(Long id, FlightDTO flightDTO);

    /**
     * Delete flight
     */
    void deleteFlight(Long id);

    /**
     * Get all flights with pagination
     */
    Page<FlightDTO> getAllFlights(Pageable pageable);

    /**
     * Get flights by origin and destination
     */
    List<FlightDTO> getFlightsByRoute(String origin, String destination);

    /**
     * Get flights by status
     */
    List<FlightDTO> getFlightsByStatus(FlightStatus status);

    /**
     * Get available flights with minimum seats
     */
    List<FlightDTO> getAvailableFlights(int minSeats);

    /**
     * Update flight status
     */
    FlightDTO updateFlightStatus(Long id, FlightStatus status);

    /**
     * Update available seats
     */
    FlightDTO updateAvailableSeats(Long id, int seats);

    /**
     * Get flights by origin with pagination
     */
    Page<FlightDTO> getFlightsByOrigin(String origin, Pageable pageable);

    /**
     * Get flights by status with pagination
     */
    Page<FlightDTO> getFlightsByStatus(FlightStatus status, Pageable pageable);

    /**
     * Get flights by route with pagination
     */
    Page<FlightDTO> getFlightsByRoute(String origin, String destination, Pageable pageable);

    /**
     * Get flight schedules for a flight with optional date range
     */
    List<FlightScheduleDTO> getFlightSchedules(Long flightId, LocalDateTime startDate, LocalDateTime endDate);
}
