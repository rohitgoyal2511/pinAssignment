# PIN manager REST service

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker (Optional)](https://www.docker.com/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.assignment.decomodigitial.DecomoDigitialApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Deploying the application to Docker

Docker Compose uses a descriptor to describe the Docker container and linking configurations. Rather than manually executing commands like the previous section, you can start the application by running:

- [Go to the project path]
- [RUN : docker-compose up]

The configuration is stored in docker-compose.yml

