Minimal Spring Boot sample app.

Requirements
For building and running the application you need:

JDK 1.8
Maven 3
Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the de.codecentric.springbootsample.Application class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run
Deploying the application to OpenShift
The easiest way to deploy the sample application to OpenShift is to use the OpenShift CLI:

oc new-app codecentric/springboot-maven3-centos~https://github.com/codecentric/springboot-sample-app
This will create:

An ImageStream called "springboot-maven3-centos"
An ImageStream called "springboot-sample-app"
A BuildConfig called "springboot-sample-app"
DeploymentConfig called "springboot-sample-app"
Service called "springboot-sample-app"
If you want to access the app from outside your OpenShift installation, you have to expose the springboot-sample-app service:

oc expose springboot-sample-app --hostname=www.example.com




Implement a PIN manager REST service

Features:
1)	Application is using inbuild h2 database
2)	User will provide Msisdn (which should be 10 to 12 digit long).
3)	API will return a 4 digit PIN corresponding to the Msisdn (not recommend to do so, but for ease API returning in this case) & a pinId (unique number).
4)	At any instance, user can have maximum of 3 active PINs.
5)	If User try to create the new one, API will return error message. 
6)	User can validate the PIN.
7)	A Pin can be active for next 1 hour (can be configurable from the code).
8)	User can attempt only 3 unsuccessfully tries, after that PIN is deleted for the DB and user needs to generate new or can give other active PIN.
9)	If PIN is not validated in 1 hour it will be removed from the DB.
10) Application have the dockerfile, by which we can easily create image and deploy.

Prerequisite:
1)	Java 8
2)	Docker
3)	Postman (will the share the postman collection in the same mail)
4)	H2 console . (http://127.0.0.1:8080/h2-console)

Api details : 
1)	Send Pin 
Curl : 
curl -X POST 'localhost:8080/docomo/pin/send' -H 'Content-Type: application/json' --data-raw '{
    "msisdn" : "9891234569"
}'

2)	Verify Pin 
Curl :
curl -X POST 'localhost:8080/docomo/pin/verify' -H 'Content-Type: application/json' --data-raw '{
    "pin" : "4152",
    "msisdn" : "9891234568",
    "pinId" : "6"
}'
