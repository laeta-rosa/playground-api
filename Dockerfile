# Stage 1: Build the application
FROM openjdk:21-jdk-slim as builder

WORKDIR /app

# Copy only the Gradle wrapper and build files first to leverage Docker cache
# If these files don't change, Docker can reuse the dependencies layer
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Copy source code only after build files (this layer is invalidated less often)
COPY src ./src

# Make gradlew executable
RUN chmod +x ./gradlew

# Build the application - dependencies will be downloaded here and cached
# Using --no-daemon for CI/CD environments
RUN ./gradlew clean bootJar -x test

# Stage 2: Create the final lean image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/playground-api-1.0-SNAPSHOT.jar ./app.jar

# Expose the port the application runs on
EXPOSE 8088

# Run the application
# Use the actual JAR name if it changes, or rename it to app.jar
CMD ["java", "-jar", "app.jar"]