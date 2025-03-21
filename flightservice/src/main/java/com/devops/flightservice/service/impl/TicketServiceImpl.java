package com.devops.flightservice.service.impl;

import com.devops.flightservice.dto.TicketDTO;
import com.devops.flightservice.mapper.TicketMapper;
import com.devops.flightservice.model.FlightSchedule;
import com.devops.flightservice.model.Ticket;
import com.devops.flightservice.repository.FlightScheduleRepository;
import com.devops.flightservice.repository.TicketRepository;
import com.devops.flightservice.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of TicketService interface.
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FlightScheduleRepository scheduleRepository;
    private final TicketMapper ticketMapper;

    @Override
    @Transactional
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        FlightSchedule schedule = scheduleRepository.findById(ticketDTO.getScheduleId())
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + ticketDTO.getScheduleId()));

        // Check if seat is available
        if (ticketRepository.existsByScheduleIdAndSeatNumber(schedule.getId(), ticketDTO.getSeatNumber())) {
            throw new IllegalStateException("Seat is already booked");
        }

        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setSchedule(schedule);
        return ticketMapper.toDTO(ticketRepository.save(ticket));
    }

    @Override
    public TicketDTO getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
        return ticketMapper.toDTO(ticket);
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new EntityNotFoundException("Ticket not found with id: " + id);
        }
        ticketRepository.deleteById(id);
    }
} 