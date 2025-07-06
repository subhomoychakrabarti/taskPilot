# TaskPilot Gateway

This is the API Gateway for the TaskPilot microservices architecture. It routes requests to the appropriate microservices and provides centralized logging, CORS handling, and request tracking.

## Architecture

The gateway routes requests to the following microservices:

- **Registration Service** (Port 8081) - Handles user registration and authentication
- **Task Service** (Port 8082) - Handles task operations and orchestration
- **Profile Service** (Port 8083) - Handles user profile data

## API Routes

| Service | Gateway Path | Target Service | Description |
|---------|--------------|----------------|-------------|
| Registration | `/api/auth/**` | `http://localhost:8081` | User registration, login, authentication |
| Tasks | `/api/tasks/**` | `http://localhost:8082` | Task CRUD operations, orchestration |
| Profiles | `/api/profiles/**` | `http://localhost:8083` | User profile management |

## Features

- **Request Routing**: Routes requests to appropriate microservices based on URL patterns
- **CORS Support**: Handles Cross-Origin Resource Sharing for frontend applications
- **Request Logging**: Logs all incoming requests with unique request IDs
- **Health Checks**: Provides health check endpoints for monitoring
- **Actuator Integration**: Spring Boot Actuator for monitoring and metrics

## Getting Started

### Prerequisites

- Java 21
- Maven 3.6+

### Running the Gateway

1. **Build the application:**
   ```bash
   mvn clean install
   ```

2. **Run the gateway:**
   ```bash
   mvn spring-boot:run
   ```

3. **Verify the gateway is running:**
   ```bash
   curl http://localhost:8080/gateway/health
   ```

### Testing the Routes

Once the gateway is running, you can test the routing:

```bash
# Health check
curl http://localhost:8080/gateway/health

# Gateway info
curl http://localhost:8080/gateway/info

# Test routing (requires target services to be running)
curl http://localhost:8080/api/auth/health
curl http://localhost:8080/api/tasks/health
curl http://localhost:8080/api/profiles/health
```

## Configuration

### Application Properties

The main configuration is in `src/main/resources/application.properties`:

- **Server Port**: 8080
- **Service Ports**: 
  - Registration Service: 8081
  - Task Service: 8082
  - Profile Service: 8083

### Customization

To modify the routing or add new services:

1. Update the routes in `application.properties`
2. Add new route configurations following the existing pattern
3. Restart the gateway

## Monitoring

### Actuator Endpoints

- `/actuator/health` - Health check
- `/actuator/info` - Application info
- `/actuator/gateway` - Gateway routes information

### Logging

The gateway logs all requests with:
- Unique request ID
- HTTP method and path
- Request headers
- Response status

## Development

### Project Structure

```
src/main/java/com/taskpilot/gateway/
├── GatewayApplication.java          # Main application class
├── config/
│   └── GatewayConfig.java          # CORS and gateway configuration
├── controller/
│   └── GatewayController.java      # Health check and info endpoints
└── filter/
    └── LoggingFilter.java          # Request logging filter
```

### Adding Custom Filters

To add custom request/response processing:

1. Create a new filter class implementing `GlobalFilter`
2. Override the `filter` method
3. Add your logic before/after `chain.filter()`
4. Implement `Ordered` interface to control filter order

## Troubleshooting

### Common Issues

1. **Service not found**: Ensure target microservices are running on the configured ports
2. **CORS errors**: Check CORS configuration in `GatewayConfig.java`
3. **Routing issues**: Verify route patterns in `application.properties`

### Debug Mode

Enable debug logging by adding to `application.properties`:
```properties
logging.level.org.springframework.cloud.gateway=DEBUG
logging.level.reactor.netty=DEBUG
```

## Next Steps

- Add authentication/authorization filters
- Implement rate limiting
- Add circuit breaker patterns
- Configure service discovery (Eureka/Consul)
- Add metrics and monitoring 