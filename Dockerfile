# Step 1: Prepare dependencies
FROM gradle:8.12.1-jdk21 AS temp_build
ARG SKIP_TESTS=false
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY src /app/src
COPY gradle /app/gradle
RUN if [ "$SKIP_TESTS" = "true" ]; then \
    gradle build --no-daemon -x test; \
  else \
    gradle build --no-daemon; \
  fi

# Step 2: Run
FROM bellsoft/liberica-openjdk-alpine-musl:21
RUN addgroup -S nonroot \
    && adduser -S nonroot -G nonroot
USER nonroot
WORKDIR /app
COPY --from=temp_build /app/build/libs/*.jar credential-issuer-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/credential-issuer-backend.jar"]
