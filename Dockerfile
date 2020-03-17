FROM maven:3.6-jdk-8-slim AS build
COPY . /usr/src/app/
WORKDIR /usr/src/app/
RUN mvn -e -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=/usr/src/app/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]