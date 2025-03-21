package com.devops.flightservice.repository;

import com.devops.flightservice.model.Flight;
import com.devops.flightservice.model.FlightSchedule;
import com.devops.flightservice.model.ScheduleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for FlightSchedule entity.
 * Provides methods for database operations and custom queries.
 */
@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
    
    /**
     * Find schedules by flight ID
     */
    List<FlightSchedule> findByFlightId(Long flightId);

    /**
     * Find schedules by status
     */
    List<FlightSchedule> findAllByFlightIdAndDateRange(Long flightId, LocalDateTime startDate, LocalDateTime endDate);
    List<FlightSchedule> findByStatus(ScheduleStatus status);

    /**
     * Find schedules by departure time range
     */
    List<FlightSchedule> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Find schedules by origin and destination with departure time range
     */
    @Query("SELECT s FROM FlightSchedule s JOIN s.flight f " +
           "WHERE f.origin = :origin AND f.destination = :destination " +
           "AND s.departureTime BETWEEN :start AND :end")
    List<FlightSchedule> findSchedulesByRouteAndTimeRange(
            @Param("origin") String origin,
            @Param("destination") String destination,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    /**
     * Find schedules by flight ID with pagination
     */
    Page<FlightSchedule> findByFlightId(Long flightId, Pageable pageable);

    /**
     * Find schedules by status with pagination
     */
    Page<FlightSchedule> findByStatus(ScheduleStatus status, Pageable pageable);

    /**
     * Find upcoming schedules
     */
    @Query("SELECT s FROM FlightSchedule s WHERE s.departureTime > :now ORDER BY s.departureTime")
    List<FlightSchedule> findUpcomingSchedules(@Param("now") LocalDateTime now);

    /**
     * Find delayed schedules
     */
    @Query("SELECT s FROM FlightSchedule s WHERE s.delayMinutes > 0")
    List<FlightSchedule> findDelayedSchedules();

    /**
     * Find all schedules for a flight
     */
    List<FlightSchedule> findByFlight(Flight flight);
    
    /**
     * Find all schedules for a flight within a date range
     */
    List<FlightSchedule> findByFlightAndDepartureTimeBetween(Flight flight, LocalDateTime startDate, LocalDateTime endDate);
}