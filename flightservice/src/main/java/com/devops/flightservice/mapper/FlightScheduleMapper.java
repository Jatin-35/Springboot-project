package com.devops.flightservice.mapper;

import com.devops.flightservice.dto.FlightScheduleDTO;
import com.devops.flightservice.model.FlightSchedule;
import org.mapstruct.*;

/**
 * Mapper interface for converting between FlightSchedule entity and DTO.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightScheduleMapper {
    
    /**
     * Converts a FlightSchedule entity to FlightScheduleDTO
     */
    FlightScheduleDTO toDTO(FlightSchedule schedule);

    /**
     * Converts a FlightScheduleDTO to FlightSchedule entity
     */
    FlightSchedule toEntity(FlightScheduleDTO scheduleDTO);

    /**
     * Updates a FlightSchedule entity with values from FlightScheduleDTO
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget FlightSchedule schedule, FlightScheduleDTO scheduleDTO);
}
