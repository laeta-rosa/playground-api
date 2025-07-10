# Playground API

REST API to manage the playground. 
- API creates and manages play sites in the playground
- API allocates kids to play sites in the playground

![hippo](https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExOGh6dGN3czFiamc2dGtiYWZkMWZmOW94ejNuNGI5cXI2ZWE5MHI0aSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/nSsP0WaX7XPZo45cwI/giphy.gif)

## Installation
The project requires the following components to be installed on the computer:

- [JDK 21](https://openjdk.org/projects/jdk/21/) - Recommended JDK version to be installed via [sdkman](https://sdkman.io/)
  is `21.0.7-amzn`.
- [Docker](https://docs.docker.com/get-docker/) - Containerization technology for building and containerizing your
  applications
- [Docker Compose](https://docs.docker.com/compose/install/) - Tool for defining and running multi-container Docker
  applications


## Usage

1. Run the following command to build the project from scratch and start necessary Docker containers:
```
docker-compose up --build
```
2. Once the application has started, call the request the API over either  
- **Swagger** -> http://localhost:8088/swagger-ui/index.html#/ (recommended as contains the API description)
- **Postman** or similar API platform

3. Call POST request to initialize a playsite with default set of attractions `POST /playsites:addDefault` 
4. Proceed with playsites adding/removing/editing by calling described requests


## Implementation notes:

- **API Responses with Embedded Status Codes**  
  All API endpoints respond with `200 OK`, even in error scenarios. The actual operation status is included in the response body using a structured `status` field, such as:
  - `ATTRACTION_CAPACITY_EXCEEDED`
  - `PLAYSITE_NOT_FOUND`
  - `KID_ALREADY_QUEUED`

  This design simplifies client handling and avoids reliance on HTTP status codes for domain-specific validation.

- **Configurable Playsite Queue Size**  
  The maximum number of kids allowed in a playsite queue is controlled by the property:
  ```
  playsite.queue.size
  ```
  This allows playground manager to fine-tune queue behavior dynamically via configuration.
- **Attraction Count Limit per Playsite**  
  To prevent system abuse , the number of attractions that can be added to a single playsite is capped. This limit is controlled by the property:
  ```
  playsite.attractions.max-count
  ```
  Attempts to exceed this configured maximum are gracefully rejected with an appropriate response status.


## Further improvements: 
- **Comprehensive Logging**  
  Introduce  contextual logging using libraries like SLF4J + Logback . This will provide better visibility into application flow and error conditions

- **Negative Scenario Integration Tests**  
  Extend the current test suite to cover edge cases and failure conditions, including:
  - Invalid request payloads
  - Exceeding resource limits (e.g. capacity overflow)
  - Missing resources

- **Limit Total Playsites in a Playground**
  - Introduce a configuration property to cap the number of playsites that can be added to a single playground