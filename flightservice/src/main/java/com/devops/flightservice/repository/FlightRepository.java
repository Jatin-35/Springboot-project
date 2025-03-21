package com.devops.flightservice.repository;

import com.devops.flightservice.model.Flight;
import com.devops.flightservice.model.FlightStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Flight entity.
 * Provides methods for database operations and custom queries.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    /**
     * Find flight by flight number
     */
    Optional<Flight> findByFlightNumber(String flightNumber);

    /**
     * Check if flight exists by flight number
     */
    boolean existsByFlightNumber(String flightNumber);

    /**
     * Find flights by origin and destination
     */
    List<Flight> findByOriginAndDestination(String origin, String destination);

    /**
     * Find flights by status
     */
    List<Flight> findByStatus(FlightStatus status);

    /**
     * Find flights with available seats greater than specified number
     */
    @Query("SELECT f FROM Flight f WHERE f.availableSeats >= :minSeats")
    List<Flight> findAvailableFlights(@Param("minSeats") int minSeats);

    /**
     * Find flights by origin with pagination
     */
    Page<Flight> findByOrigin(String origin, Pageable pageable);

    /**
     * Find flights by status with pagination
     */
    Page<Flight> findByStatus(FlightStatus status, Pageable pageable);

    /**
     * Find flights by origin and destination with pagination
     */
    Page<Flight> findByOriginAndDestination(String origin, String destination, Pageable pageable);
}