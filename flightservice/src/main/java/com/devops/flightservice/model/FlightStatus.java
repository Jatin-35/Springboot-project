package com.devops.flightservice.model;

/**
 * Enum representing the possible statuses of a flight.
 */
public enum FlightStatus {
    SCHEDULED,      // Flight is scheduled but not yet active
    ACTIVE,         // Flight is currently active
    DELAYED,        // Flight is delayed
    CANCELLED,      // Flight has been cancelled
    COMPLETED,      // Flight has completed its journey
    MAINTENANCE,    // Flight is under maintenance
    RETIRED         // Flight has been retired from service
} 