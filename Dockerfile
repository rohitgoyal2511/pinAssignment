FROM openjdk:8
ADD target/docomodigital-api.jar docomodigital-api.jar
ENTRYPOINT ["java","-jar","/docomodigital-api.jar"]