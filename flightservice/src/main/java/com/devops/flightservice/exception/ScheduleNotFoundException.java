package com.devops.flightservice.exception;

/**
 * Exception thrown when a flight schedule is not found in the system.
 */
public class ScheduleNotFoundException extends RuntimeException {
    
    public ScheduleNotFoundException(String message) {
        super(message);
    }
    
    public ScheduleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
} 