FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/spring-boot-jpa-postgresql-0.0.1-SNAPSHOT.jar spring-boot-jpa-postgresql-1.jar
ENTRYPOINT ["java","-jar","/spring-boot-jpa-postgresql-1.jar"]