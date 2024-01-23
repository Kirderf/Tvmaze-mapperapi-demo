# TVMaze Mapper Demo

This is a Spring Boot application that demonstrates integration with the TVMaze API to retrieve and map TV shows. The application provides RESTful endpoints to fetch information about TV shows, such as all shows, popular shows, and shows by genre.

## TvshowServiceImpl

The `TvshowServiceImpl` class implements the `TvshowService` interface and is responsible for fetching TV show data from the TVMaze API. It uses Spring's `WebClient` to make reactive web requests and retrieve a Flux of TV shows. The data is fetched in paginated fashion (300 pages hardcoded in the current version), and the results are concatenated into a single Flux. The results are also cached using Spring's `@Cacheable` annotation.

## TvshowController

The `TvshowController` class defines RESTful endpoints for accessing TV show information. It uses Spring's `WebFlux` for reactive programming.

### Endpoints:

1. **GET /tvshows/all**
   - Summary: Get all TV shows.
   - Access: Requires the 'ADMIN' role.
   - Response: Returns a list of all TV shows.

2. **GET /tvshows/popular**
   - Summary: Get the 100 most popular TV shows.
   - Response: Returns a list of the top 100 TV shows sorted by weight.

3. **GET /tvshows/genres/{genre}**
   - Summary: Get all TV shows by genre.
   - Access: Requires the 'ADMIN' role.
   - Path Variable: `genre` - The genre to filter by.
   - Response: Returns a list of TV shows filtered by the specified genre.

### Logging:
The controllers log information using SLF4J. Logging is utilized to track method calls and important events in the application.

### Swagger:
The application integrates with Swagger for API documentation. You can access the Swagger UI at `/swagger-ui.html`.

### TESTING:
Testing is not considered a focus for this task. So faults may occur!

## Dependencies:

- **Spring Boot Starter WebFlux**: For building reactive web applications.
- **SpringDoc OpenAPI Starter**: For generating OpenAPI documentation.
- **Spring Boot Starter Security**: For securing the application.
- **Spring Boot OAuth2 Resource Server**: For OAuth2 resource server capabilities.
- **Reactor Test**: For testing reactive components.
- **JUnit Platform**: For running JUnit tests.

## Usage:

1. Clone the repository.
2. Replace values in application.properties
2. Build the application using Gradle (`./gradlew build`).
3. Run the application (`./gradlew bootRun`).
4. Access the API documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

