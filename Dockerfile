FROM openjdk:17-jdk-slim

MAINTAINER wfarooq.com

COPY target/profile-service-0.0.1-SNAPSHOT.jar profile-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","profile-service-0.0.1-SNAPSHOT.jar"]
