# Airline Management System

A modern microservice-based airline management system built with Spring Boot. This system allows users to manage flights, schedules, users, and ticket bookings through RESTful APIs.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-green)
![Maven](https://img.shields.io/badge/Maven-3.8.1-blue)

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Screenshots](#screenshots)

## Features

- **Flight Management:** Create and manage flights and their schedules
- **User Management:** Register and manage users
- **Ticket Booking:** Book, view and cancel flight tickets
- **Validation:**  database validation with constraints
- **Error Handling:** Centralized exception handling

## Project Structure

The system follows a microservice architecture with the following package structure:

```
com.devops.xservice/
├── controller/             # REST controllers for handling HTTP requests
├── dto/                    # Data Transfer Objects for API requests and responses
├── exception/              # Custom exceptions and global exception handling
├── mapper/                 # Object mappers for converting between entities and DTOs
├── model/                  # Domain models and entities
├── repository/             # Data access interfaces for database operations
├── service/                # Business logic implementation
├── validation/             # Custom validation rules and constraints
└── XServiceApplication.java  # Main application entry point
```

## Technologies Used

- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **Lombok**
- **MapStruct**
- **Maven**

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+

### Installation

1. Clone the repository:

```bash
git clone https://github.com/PiyushVyas13/airline-system-microservices.git
cd airline-system-microservices
```

2. Build the project:

```bash
mvn clean install
```

## API Documentation

### Flight Service API

Base URL: `http://localhost:8080/flights`

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/?sort=` | GET | Get all flights|
| `/` | POST | Create a new flight |
| `/{id}` | GET | Get flight by ID |
| `/{id}/schedules?dates=` | GET | Get schedules for a flight (with date range)|
| `/schedules/{id}` | GET | Get flight schedule by Id |


### User Service API

Base URL: `http://localhost:8080/users`

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | POST | Create a new user |
| `/{id}` | GET | Get user by ID |

### Ticket Service API

Base URL: `http://localhost:8080/tickets`

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | POST | Create/book a new ticket |
| `/{id}` | GET | Get ticket by ID |
| `/{id}` | DELETE | Cancel a ticket |


## Screenshots

### Project Structure
![image](https://github.com/user-attachments/assets/d785c40d-e793-46c7-a70a-939dbbf15844)


### API Testing

#### Flight Service Endpoints

##### Getting flights
![image](https://github.com/user-attachments/assets/4cf17db1-839a-4537-bcbf-3ad31af5e153)
![image](https://github.com/user-attachments/assets/2d710226-32b3-4e9c-af3b-90a96e316980)

##### Getting individual flight
![image](https://github.com/user-attachments/assets/19bea1bf-2df3-484a-9fa9-0f707fdab97e)

##### Getting flight schedules
![image](https://github.com/user-attachments/assets/ae1c9c25-83d9-4c7c-8036-8b1d80f16125)
![image](https://github.com/user-attachments/assets/0936d229-868b-4860-b5d0-28a3dffd7f15)
![image](https://github.com/user-attachments/assets/c17d8453-1f53-46ba-b951-c92fdd368fe5)


##### Creating a flight
![image](https://github.com/user-attachments/assets/e055dc52-d276-4dbc-ad87-8cbd8e083f20)



#### User Service Endpoints

##### Getting all users
![image](https://github.com/user-attachments/assets/7829c40c-f018-42b9-8058-ad95fb622c8b)

##### Getting a specific user
![image](https://github.com/user-attachments/assets/ba08b001-5cf7-40ef-ad6a-aa49cbc2cb04)

##### Creating a user
![image](https://github.com/user-attachments/assets/712b8429-71c5-4437-b69f-1e186348919f)


#### Ticket Service Endpoints

##### Booking a ticket
![image](https://github.com/user-attachments/assets/93d921e4-c964-4e39-9c71-d7e5ebb0ab17)


##### Getting a ticket
![image](https://github.com/user-attachments/assets/40234553-b666-4170-b23b-c53830eef85d)


##### Cancelling a ticket
![image](https://github.com/user-attachments/assets/1dcb3f66-01c4-4b72-8a7a-0d4dd633da61)
![image](https://github.com/user-attachments/assets/004e88ed-e5ab-42ca-bf17-4b967a8b32b1)



### Validation Examples
![image](https://github.com/user-attachments/assets/cd1f06c2-55d9-4d1e-aa49-3cef67f76d34)
![image](https://github.com/user-attachments/assets/bb8f9557-cdb5-4343-bd5f-3104d275a1fe)
