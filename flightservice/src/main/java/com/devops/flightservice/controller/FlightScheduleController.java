package com.devops.flightservice.controller;

import com.devops.flightservice.dto.FlightScheduleDTO;
import com.devops.flightservice.model.ScheduleStatus;
import com.devops.flightservice.service.FlightScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller for FlightSchedule operations.
 * Provides endpoints for managing flight schedules.
 */
@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class FlightScheduleController {

    private final FlightScheduleService scheduleService;

    /**
     * Create a new flight schedule
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightScheduleDTO createSchedule(@Valid @RequestBody FlightScheduleDTO scheduleDTO) {
        return scheduleService.createSchedule(scheduleDTO);
    }

    /**
     * Get schedule by ID
     */
    @GetMapping("/{id}")
    public FlightScheduleDTO getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    /**
     * Update schedule details
     */
    @PutMapping("/{id}")
    public FlightScheduleDTO updateSchedule(@PathVariable Long id, @Valid @RequestBody FlightScheduleDTO scheduleDTO) {
        return scheduleService.updateSchedule(id, scheduleDTO);
    }

    /**
     * Delete schedule
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }

    /**
     * Get all schedules with pagination
     */
    @GetMapping
    public Page<FlightScheduleDTO> getAllSchedules(Pageable pageable) {
        return scheduleService.getAllSchedules(pageable);
    }

    /**
     * Get schedules by flight ID
     */
    @GetMapping("/flight/{flightId}")
    public List<FlightScheduleDTO> getSchedulesByFlightId(@PathVariable Long flightId) {
        return scheduleService.getSchedulesByFlightId(flightId);
    }

    /**
     * Get schedules by status
     */
    @GetMapping("/status/{status}")
    public List<FlightScheduleDTO> getSchedulesByStatus(@PathVariable ScheduleStatus status) {
        return scheduleService.getSchedulesByStatus(status);
    }

    /**
     * Get schedules by time range
     */
    @GetMapping("/time-range")
    public List<FlightScheduleDTO> getSchedulesByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return scheduleService.getSchedulesByTimeRange(start, end);
    }

    /**
     * Get schedules by route and time range
     */
    @GetMapping("/route-time-range")
    public List<FlightScheduleDTO> getSchedulesByRouteAndTimeRange(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return scheduleService.getSchedulesByRouteAndTimeRange(origin, destination, start, end);
    }

    /**
     * Get schedules by flight ID with pagination
     */
    @GetMapping("/flight/{flightId}/page")
    public Page<FlightScheduleDTO> getSchedulesByFlightId(
            @PathVariable Long flightId,
            Pageable pageable) {
        return scheduleService.getSchedulesByFlightId(flightId, pageable);
    }

    /**
     * Get schedules by status with pagination
     */
    @GetMapping("/status/{status}/page")
    public Page<FlightScheduleDTO> getSchedulesByStatus(
            @PathVariable ScheduleStatus status,
            Pageable pageable) {
        return scheduleService.getSchedulesByStatus(status, pageable);
    }

    /**
     * Get upcoming schedules
     */
    @GetMapping("/upcoming")
    public List<FlightScheduleDTO> getUpcomingSchedules() {
        return scheduleService.getUpcomingSchedules();
    }

    /**
     * Get delayed schedules
     */
    @GetMapping("/delayed")
    public List<FlightScheduleDTO> getDelayedSchedules() {
        return scheduleService.getDelayedSchedules();
    }

    /**
     * Update schedule status
     */
    @PatchMapping("/{id}/status")
    public FlightScheduleDTO updateScheduleStatus(
            @PathVariable Long id,
            @RequestParam ScheduleStatus status) {
        return scheduleService.updateScheduleStatus(id, status);
    }

    /**
     * Update schedule delay
     */
    @PatchMapping("/{id}/delay")
    public FlightScheduleDTO updateScheduleDelay(
            @PathVariable Long id,
            @RequestParam int delayMinutes) {
        return scheduleService.updateScheduleDelay(id, delayMinutes);
    }
} 