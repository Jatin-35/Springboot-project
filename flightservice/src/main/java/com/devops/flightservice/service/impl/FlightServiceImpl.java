package com.devops.flightservice.service.impl;

import com.devops.flightservice.dto.FlightDTO;
import com.devops.flightservice.dto.FlightScheduleDTO;
import com.devops.flightservice.exception.FlightNotFoundException;
import com.devops.flightservice.mapper.FlightMapper;
import com.devops.flightservice.mapper.FlightScheduleMapper;
import com.devops.flightservice.model.Flight;
import com.devops.flightservice.model.FlightSchedule;
import com.devops.flightservice.repository.FlightRepository;
import com.devops.flightservice.repository.FlightScheduleRepository;
import com.devops.flightservice.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of FlightService interface.
 * Provides business logic for flight management operations.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightScheduleRepository scheduleRepository;
    private final FlightMapper flightMapper;
    private final FlightScheduleMapper scheduleMapper;

    @Override
    @Transactional
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);
        flight.setStatus(FlightStatus.SCHEDULED);
        return flightMapper.toDTO(flightRepository.save(flight));
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return flightRepository.findById(id)
                .map(flightMapper::toDTO)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
    }

    @Override
    public FlightDTO getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .map(flightMapper::toDTO)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with number: " + flightNumber));
    }

    @Override
    @Transactional
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
        
        flightMapper.updateEntity(existingFlight, flightDTO);
        return flightMapper.toDTO(flightRepository.save(existingFlight));
    }

    @Override
    @Transactional
    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }

    @Override
    public Page<FlightDTO> getAllFlights(Pageable pageable) {
        return flightRepository.findAll(pageable).map(flightMapper::toDTO);
    }

    @Override
    public List<FlightDTO> getFlightsByRoute(String origin, String destination) {
        return flightRepository.findByOriginAndDestination(origin, destination)
                .stream()
                .map(flightMapper::toDTO)
                .toList();
    }

    @Override
    public List<FlightDTO> getFlightsByStatus(FlightStatus status) {
        return flightRepository.findByStatus(status)
                .stream()
                .map(flightMapper::toDTO)
                .toList();
    }

    @Override
    public List<FlightDTO> getAvailableFlights(int minSeats) {
        return flightRepository.findAvailableFlights(minSeats)
                .stream()
                .map(flightMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public FlightDTO updateFlightStatus(Long id, FlightStatus status) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
        flight.setStatus(status);
        return flightMapper.toDTO(flightRepository.save(flight));
    }

    @Override
    @Transactional
    public FlightDTO updateAvailableSeats(Long id, int seats) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
        
        if (seats > flight.getTotalCapacity()) {
            throw new IllegalArgumentException("Cannot set available seats greater than total capacity");
        }
        
        flight.setAvailableSeats(seats);
        return flightMapper.toDTO(flightRepository.save(flight));
    }

    @Override
    public Page<FlightDTO> getFlightsByOrigin(String origin, Pageable pageable) {
        return flightRepository.findByOrigin(origin, pageable).map(flightMapper::toDTO);
    }

    @Override
    public Page<FlightDTO> getFlightsByStatus(FlightStatus status, Pageable pageable) {
        return flightRepository.findByStatus(status, pageable).map(flightMapper::toDTO);
    }

    @Override
    public Page<FlightDTO> getFlightsByRoute(String origin, String destination, Pageable pageable) {
        return flightRepository.findByOriginAndDestination(origin, destination, pageable)
                .map(flightMapper::toDTO);
    }

    @Override
    public List<FlightScheduleDTO> getFlightSchedules(Long flightId, LocalDateTime startDate, LocalDateTime endDate) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + flightId));
        
        List<FlightSchedule> schedules;
        if (startDate != null && endDate != null) {
            schedules = scheduleRepository.findByFlightAndDepartureTimeBetween(flight, startDate, endDate);
        } else {
            schedules = scheduleRepository.findByFlight(flight);
        }
        
        return schedules.stream()
                .map(scheduleMapper::toDTO)
                .collect(Collectors.toList());
    }
} 