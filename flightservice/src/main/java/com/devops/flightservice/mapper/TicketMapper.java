package com.devops.flightservice.mapper;

import com.devops.flightservice.dto.TicketDTO;
import com.devops.flightservice.model.Ticket;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Ticket entity and DTO.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {
    
    /**
     * Converts a Ticket entity to TicketDTO
     */
    TicketDTO toDTO(Ticket ticket);

    /**
     * Converts a TicketDTO to Ticket entity
     */
    Ticket toEntity(TicketDTO ticketDTO);

    /**
     * Updates a Ticket entity with values from TicketDTO
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Ticket ticket, TicketDTO ticketDTO);
} 