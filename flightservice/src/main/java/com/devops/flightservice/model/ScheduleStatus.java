package com.devops.flightservice.model;

/**
 * Enum representing the possible statuses of a flight schedule.
 */
public enum ScheduleStatus {
    PLANNED,        // Schedule is planned but not yet confirmed
    CONFIRMED,      // Schedule has been confirmed
    CHECK_IN,       // Passengers are checking in
    BOARDING,       // Passengers are boarding
    DEPARTED,       // Flight has departed
    IN_AIR,         // Flight is in the air
    LANDED,         // Flight has landed
    DELAYED,        // Schedule is delayed
    CANCELLED,      // Schedule has been cancelled
    COMPLETED       // Schedule has been completed
} 