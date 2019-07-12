FROM openjdk:8-jdk-alpine

COPY target/Warikan-0.0.1-SNAPSHOT.jar Warikan-0.0.1.jar

ENTRYPOINT ["java","-jar","/Warikan-0.0.1.jar"]