# Step 1: Preparate the environment
FROM gradle:8.12.1-jdk17 AS dependencies
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle
RUN ./gradlew dependencies --no-daemon

# Step 2: Build
FROM gradle:8.12.1-jdk17 AS build
WORKDIR /app
COPY . . 
RUN ./gradlew clean bootJar --no-daemon

# Step 3: Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar credential-issuer-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/credential-issuer-backend.jar"]