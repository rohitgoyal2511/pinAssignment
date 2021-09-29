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

- Go to the project path
- RUN : docker-compose up

The configuration is stored in docker-compose.yml

# Application Features:

-	User will provide Msisdn (which should be 10 to 12 digit long).
-	API will provide a 4 digit PIN corresponding to the Msisdn (not recommend to do so but for ease, API returning in this case) & a pinId (unique uuid).
-	At any instance, user can have maximum of 3 active PINs(not validated).
-	If user has 3 active PINs, API will return error on the subsequent request. 
-	User can validate the PIN corresponding to pinId.
-	A Pin can be active for next 1 hour (can be configurable from the code).
-	User can attempt only 3 unsuccessfully tries, after that PIN is deleted from the DB and user needs to generate new or will give other active PIN.
-	Application using H2 database. Data will persist till application restart. You can login to console at - http://127.0.0.1:9090/docomo/h2-console.
-   Application have the dockerfile, by which we can easily create image and deploy.

# Api Details:

- Send Pin : HTTP POST API("docomo/pin/send") which takes the msisdn and sends the PIN to the user.
- Validate Pin -  HTTP POST API("docomo/pin/verify") which validated the Pin corresponding to the Pin Id.

Postman Collection link - https://www.getpostman.com/collections/8002d4116e8446792eab




