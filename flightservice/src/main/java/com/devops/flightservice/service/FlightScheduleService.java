package com.devops.flightservice.service;

import com.devops.flightservice.dto.FlightScheduleDTO;
import com.devops.flightservice.model.ScheduleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface for FlightSchedule operations.
 * Defines business logic methods for flight schedule management.
 */
public interface FlightScheduleService {
    
    /**
     * Create a new flight schedule
     */
    FlightScheduleDTO createSchedule(FlightScheduleDTO scheduleDTO);

    /**
     * Get schedule by ID
     */
    FlightScheduleDTO getScheduleById(Long id);

    /**
     * Update schedule details
     */
    FlightScheduleDTO updateSchedule(Long id, FlightScheduleDTO scheduleDTO);

    /**
     * Delete schedule
     */
    void deleteSchedule(Long id);

    /**
     * Get all schedules with pagination
     */
    Page<FlightScheduleDTO> getAllSchedules(Pageable pageable);

    /**
     * Get schedules by flight ID
     */
    List<FlightScheduleDTO> getSchedulesByFlightId(Long flightId);

    /**
     * Get schedules by status
     */
    List<FlightScheduleDTO> getSchedulesByStatus(ScheduleStatus status);

    /**
     * Get schedules by departure time range
     */
    List<FlightScheduleDTO> getSchedulesByTimeRange(LocalDateTime start, LocalDateTime end);

    /**
     * Get schedules by route and time range
     */
    List<FlightScheduleDTO> getSchedulesByRouteAndTimeRange(
            String origin,
            String destination,
            LocalDateTime start,
            LocalDateTime end);

    /**
     * Get schedules by flight ID with pagination
     */
    Page<FlightScheduleDTO> getSchedulesByFlightId(Long flightId, Pageable pageable);

    /**
     * Get schedules by status with pagination
     */
    Page<FlightScheduleDTO> getSchedulesByStatus(ScheduleStatus status, Pageable pageable);

    /**
     * Get upcoming schedules
     */
    List<FlightScheduleDTO> getUpcomingSchedules();

    /**
     * Get delayed schedules
     */
    List<FlightScheduleDTO> getDelayedSchedules();

    /**
     * Update schedule status
     */
    FlightScheduleDTO updateScheduleStatus(Long id, ScheduleStatus status);

    /**
     * Update schedule delay
     */
    FlightScheduleDTO updateScheduleDelay(Long id, int delayMinutes);
} 