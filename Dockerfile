# Maven
FROM maven:3-openjdk-17-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean -e -B package

# Java
FROM eclipse-temurin:17.0.3_7-jdk
WORKDIR /app
COPY --from=builder /app/target/event-streaming.jar .
CMD exec java -jar event-streaming.jar
