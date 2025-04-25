FROM maven:3.9.4-eclipse-temurin-21-alpine AS dependencies
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY --from=dependencies /root/.m2 /root/.m2
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]