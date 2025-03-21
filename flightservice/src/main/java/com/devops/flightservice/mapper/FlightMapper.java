package com.devops.flightservice.mapper;

import com.devops.flightservice.dto.FlightDTO;
import com.devops.flightservice.model.Flight;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Flight entity and DTO.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightMapper {
    
    /**
     * Converts a Flight entity to FlightDTO
     */
    FlightDTO toDTO(Flight flight);

    /**
     * Converts a FlightDTO to Flight entity
     */
    Flight toEntity(FlightDTO flightDTO);

    /**
     * Updates a Flight entity with values from FlightDTO
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Flight flight, FlightDTO flightDTO);
}
