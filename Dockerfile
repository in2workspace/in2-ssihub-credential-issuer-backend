# Step 1: Prepare dependencies
FROM gradle:8.12.1-jdk17 AS dependencies
WORKDIR /app
COPY gradlew .
COPY build.gradle settings.gradle ./
COPY gradle gradle
RUN gradle dependencies --no-daemon;


# Step 1
FROM docker.io/gradle:8.12.1-jdk17 AS temp_build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon;

# Step 3: Run
FROM bellsoft/liberica-openjdk-alpine-musl:17
RUN addgroup -S nonroot \
    && adduser -S nonroot -G nonroot
USER nonroot
WORKDIR /app
COPY --from=temp_build /app/build/libs/*.jar credential-issuer-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/credential-issuer-backend.jar"]



