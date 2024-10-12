FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml dependency:go-offline

COPY . /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /app/target/bank-1.0-SNAPSHOT.jar bank.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bank.jar"]
