# AOODAX Clean Architecture Sample

An application structured using the principles of Clean Architecture with Spring Boot. This project ensures the
separation of concerns, making sure that the core business logic is decoupled from external details such as persistence
and web interfaces.

## Architecture Overview

The project is structured into several modules to clearly define their responsibilities:

### Modules:

1. **Configuration**:
    - Contains global configuration details.

2. **Contract**:
    - **Boundary Input/Output**: Defines the expected input/output for interacting with core business logic.
    - **Model**: Contains business domain models.

3. **Entrypoint**:
    - **Integration Test**: Integration tests for entry points.
    - **Queue**:
        * **Consumer**: Processes incoming queue messages.
        * **Message**: Defines the structure and types of queue messages.
    - **REST**:
        * **API**: RESTful endpoints for external interactions.
        * **DTO**: Data Transfer Objects used for REST interactions.

4. **Infrastructure**:
    - **Domain**: Contains `@Entity` or `@Document` annotated classes, reflecting the persistence mechanism.
    - **Messaging**: Messaging configurations and integrations.
    - **Repository**: Interfaces and implementations for data access.

5. **Test Toolkit**:
    - **Integration**: Tools and utilities for integration testing.
    - **Unit**: Tools and utilities for unit testing.

6. **Usecase**:
    - **Impl**: Implementation of the application's use cases.
    - **Integration Test**: Tests to validate use cases.

## Key Considerations

- **Decoupling**: The architecture is designed to decouple business logic (`Contract` and `Usecase`) from external
  details like databases and frameworks (`Infrastructure` and `Entrypoint`).

- **Model vs. Entity**: The term "model" is used to differentiate domain models from Spring's `@Entity` or `@Document`
  classes. Domain models reside in the `Contract` module, while `@Entity` classes are in the `Infrastructure` module.

- **Repository**: Repositories handle the transformation between domain models and database entities, ensuring that
  business logic remains unaffected by changes in the persistence mechanism.

## Getting Started

1. Clone the repository: ```git clone git@github.com:gerasghulyan/aoodax-clean-architecture.git```
2. Navigate to the project directory.
3. Install the required dependencies using Gradle:```./gradlew build```
4. Run the Spring Boot application:
   ```./gradlew bootRun```

## Contributing

For those wishing to contribute, please adhere to the architecture guidelines mentioned in this README. It's crucial to
keep business logic separate from external frameworks or databases.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

---



![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)