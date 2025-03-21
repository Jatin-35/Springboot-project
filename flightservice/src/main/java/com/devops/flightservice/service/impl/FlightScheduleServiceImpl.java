package com.devops.flightservice.service.impl;

import com.devops.flightservice.dto.FlightScheduleDTO;
import com.devops.flightservice.exception.FlightNotFoundException;
import com.devops.flightservice.exception.ScheduleNotFoundException;
import com.devops.flightservice.mapper.FlightScheduleMapper;
import com.devops.flightservice.model.Flight;
import com.devops.flightservice.model.FlightSchedule;
import com.devops.flightservice.model.ScheduleStatus;
import com.devops.flightservice.repository.FlightRepository;
import com.devops.flightservice.repository.FlightScheduleRepository;
import com.devops.flightservice.service.FlightScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of FlightScheduleService interface.
 * Provides business logic for flight schedule management operations.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final FlightScheduleRepository scheduleRepository;
    private final FlightRepository flightRepository;
    private final FlightScheduleMapper scheduleMapper;

    @Override
    @Transactional
    public FlightScheduleDTO createSchedule(FlightScheduleDTO scheduleDTO) {
        Flight flight = flightRepository.findById(scheduleDTO.getFlightId())
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + scheduleDTO.getFlightId()));
        
        FlightSchedule schedule = scheduleMapper.toEntity(scheduleDTO);
        schedule.setFlight(flight);
        schedule.setStatus(ScheduleStatus.PLANNED);
        
        return scheduleMapper.toDTO(scheduleRepository.save(schedule));
    }

    @Override
    public FlightScheduleDTO getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .map(scheduleMapper::toDTO)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found with id: " + id));
    }

    @Override
    @Transactional
    public FlightScheduleDTO updateSchedule(Long id, FlightScheduleDTO scheduleDTO) {
        FlightSchedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found with id: " + id));
        
        if (scheduleDTO.getFlightId() != null && !scheduleDTO.getFlightId().equals(existingSchedule.getFlight().getId())) {
            Flight newFlight = flightRepository.findById(scheduleDTO.getFlightId())
                    .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + scheduleDTO.getFlightId()));
            existingSchedule.setFlight(newFlight);
        }
        
        scheduleMapper.updateEntity(existingSchedule, scheduleDTO);
        return scheduleMapper.toDTO(scheduleRepository.save(existingSchedule));
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException("Schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }

    @Override
    public Page<FlightScheduleDTO> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable).map(scheduleMapper::toDTO);
    }

    @Override
    public List<FlightScheduleDTO> getSchedulesByFlightId(Long flightId) {
        return scheduleRepository.findByFlightId(flightId)
                .stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    @Override
    public List<FlightScheduleDTO> getSchedulesByStatus(ScheduleStatus status) {
        return scheduleRepository.findByStatus(status)
                .stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    @Override
    public List<FlightScheduleDTO> getSchedulesByTimeRange(LocalDateTime start, LocalDateTime end) {
        return scheduleRepository.findByDepartureTimeBetween(start, end)
                .stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    @Override
    public List<FlightScheduleDTO> getSchedulesByRouteAndTimeRange(
            String origin,
            String destination,
            LocalDateTime start,
            LocalDateTime end) {
        return scheduleRepository.findSchedulesByRouteAndTimeRange(origin, destination, start, end)
                .stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    @Override
    public Page<FlightScheduleDTO> getSchedulesByFlightId(Long flightId, Pageable pageable) {
        return scheduleRepository.findByFlightId(flightId, pageable).map(scheduleMapper::toDTO);
    }

    @Override
    public Page<FlightScheduleDTO> getSchedulesByStatus(ScheduleStatus status, Pageable pageable) {
        return scheduleRepository.findByStatus(status, pageable).map(scheduleMapper::toDTO);
    }

    @Override
    public List<FlightScheduleDTO> getUpcomingSchedules() {
        return scheduleRepository.findUpcomingSchedules(LocalDateTime.now())
                .stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    @Override
    public List<FlightScheduleDTO> getDelayedSchedules() {
        return scheduleRepository.findDelayedSchedules()
                .stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public FlightScheduleDTO updateScheduleStatus(Long id, ScheduleStatus status) {
        FlightSchedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found with id: " + id));
        schedule.setStatus(status);
        return scheduleMapper.toDTO(scheduleRepository.save(schedule));
    }

    @Override
    @Transactional
    public FlightScheduleDTO updateScheduleDelay(Long id, int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }
        
        FlightSchedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found with id: " + id));
        schedule.setDelayMinutes(delayMinutes);
        return scheduleMapper.toDTO(scheduleRepository.save(schedule));
    }
} 