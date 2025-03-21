package com.devops.flightservice.controller;

import com.devops.flightservice.dto.FlightDTO;
import com.devops.flightservice.dto.FlightScheduleDTO;
import com.devops.flightservice.model.FlightStatus;
import com.devops.flightservice.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller for Flight operations.
 * Provides endpoints for managing flights.
 */
@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    /**
     * Create a new flight
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    /**
     * Get flight by ID
     */
    @GetMapping("/{id}")
    public FlightDTO getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    /**
     * Get flight by flight number
     */
    @GetMapping("/number/{flightNumber}")
    public FlightDTO getFlightByNumber(@PathVariable String flightNumber) {
        return flightService.getFlightByNumber(flightNumber);
    }

    /**
     * Update flight details
     */
    @PutMapping("/{id}")
    public FlightDTO updateFlight(@PathVariable Long id, @Valid @RequestBody FlightDTO flightDTO) {
        return flightService.updateFlight(id, flightDTO);
    }

    /**
     * Delete flight
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    /**
     * Get all flights with pagination
     */
    @GetMapping
    public Page<FlightDTO> getAllFlights(Pageable pageable) {
        return flightService.getAllFlights(pageable);
    }

    /**
     * Get flights by route
     */
    @GetMapping("/route")
    public List<FlightDTO> getFlightsByRoute(
            @RequestParam String origin,
            @RequestParam String destination) {
        return flightService.getFlightsByRoute(origin, destination);
    }

    /**
     * Get flights by status
     */
    @GetMapping("/status/{status}")
    public List<FlightDTO> getFlightsByStatus(@PathVariable FlightStatus status) {
        return flightService.getFlightsByStatus(status);
    }

    /**
     * Get available flights
     */
    @GetMapping("/available")
    public List<FlightDTO> getAvailableFlights(@RequestParam(defaultValue = "1") int minSeats) {
        return flightService.getAvailableFlights(minSeats);
    }

    /**
     * Update flight status
     */
    @PatchMapping("/{id}/status")
    public FlightDTO updateFlightStatus(
            @PathVariable Long id,
            @RequestParam FlightStatus status) {
        return flightService.updateFlightStatus(id, status);
    }

    /**
     * Update available seats
     */
    @PatchMapping("/{id}/seats")
    public FlightDTO updateAvailableSeats(
            @PathVariable Long id,
            @RequestParam int seats) {
        return flightService.updateAvailableSeats(id, seats);
    }

    /**
     * Get flights by origin with pagination
     */
    @GetMapping("/origin/{origin}")
    public Page<FlightDTO> getFlightsByOrigin(
            @PathVariable String origin,
            Pageable pageable) {
        return flightService.getFlightsByOrigin(origin, pageable);
    }

    /**
     * Get flights by status with pagination
     */
    @GetMapping("/status/{status}/page")
    public Page<FlightDTO> getFlightsByStatus(
            @PathVariable FlightStatus status,
            Pageable pageable) {
        return flightService.getFlightsByStatus(status, pageable);
    }

    /**
     * Get flights by route with pagination
     */
    @GetMapping("/route/page")
    public Page<FlightDTO> getFlightsByRoute(
            @RequestParam String origin,
            @RequestParam String destination,
            Pageable pageable) {
        return flightService.getFlightsByRoute(origin, destination, pageable);
    }

    /**
     * Get flight schedules by flight ID and optional date range
     */
    @GetMapping("/{id}/schedules")
    public List<FlightScheduleDTO> getFlightSchedules(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return flightService.getFlightSchedules(id, startDate, endDate);
    }
}
