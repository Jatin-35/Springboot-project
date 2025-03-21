package com.devops.flightservice.exception;

/**
 * Exception thrown when a flight is not found in the system.
 */
public class FlightNotFoundException extends RuntimeException {
    
    public FlightNotFoundException(String message) {
        super(message);
    }
    
    public FlightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
} 