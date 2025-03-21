package com.devops.flightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Airline Schedule Management Service
 * 
 * This microservice handles all flight scheduling operations including:
 * - Flight creation and management
 * - Schedule management
 * - Route planning
 * - Availability checking
 * 
 * @author System Administrator
 * @version 1.0
 */
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class AirlineScheduleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineScheduleServiceApplication.class, args);
    }
}
