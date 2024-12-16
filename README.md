# gametunes-auth

## Description
`gametunes-auth` is a Spring Boot application that provides authentication services for the GameTunes platform.

## Requirements
- Java 21
- Maven 3.8.1 or higher

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Security
- Lombok
- Spring Boot Starter Test
- Spring Security Test
- Spring Boot Starter Validation
- Spring Boot DevTools
- JJWT (Java JWT)
- Jackson Annotations
- Jakarta Servlet API
- Spring Boot Starter Data MongoDB

## Configuration
The application uses a MongoDB database. Update the `src/main/resources/secret.properties` file with your MongoDB connection details and other secret properties.