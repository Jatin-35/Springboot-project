package com.devops.flightservice.repository;

import com.devops.flightservice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Ticket entity.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    /**
     * Check if a seat is already booked for a schedule
     */
    boolean existsByScheduleIdAndSeatNumber(Long scheduleId, Integer seatNumber);
} 