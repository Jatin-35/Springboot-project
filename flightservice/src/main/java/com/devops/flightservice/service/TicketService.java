package com.devops.flightservice.service;

import com.devops.flightservice.dto.TicketDTO;

/**
 * Service interface for Ticket operations.
 */
public interface TicketService {
    
    /**
     * Create a new ticket
     */
    TicketDTO createTicket(TicketDTO ticketDTO);

    /**
     * Get ticket by ID
     */
    TicketDTO getTicketById(Long id);

    /**
     * Delete ticket
     */
    void deleteTicket(Long id);
} 