FROM openjdk:8
copy ./target/docomodigital-0.0.1-SNAPSHOT.jar docomodigital.jar
ENTRYPOINT ["java","-jar","/docomodigital.jar"]