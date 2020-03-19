FROM maven:3.6-jdk-8-slim AS build
COPY . /usr/src/app/
WORKDIR /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /
COPY --from=build /usr/src/app/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]