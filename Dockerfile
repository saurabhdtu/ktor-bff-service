# Use the official Gradle image to build the application
FROM gradle:7.6.0-jdk17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle project files, including the wrapper files
COPY . /app/

# Ensure the Gradle wrapper script has execute permissions
RUN chmod +x ./gradlew

# Build the JAR file using the Gradle wrapper
RUN ./gradlew --no-daemon buildInclusiveJar

# Check the contents of the build/libs directory to verify JAR generation
RUN ls -alh /app/build/libs

# Use a minimal base image with JRE 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the new image
WORKDIR /app

# Copy the generated fat JAR from the build stage
COPY --from=build /app/build/libs/bff.jar /app/bff.jar

# Check if the JAR is copied correctly
RUN ls -alh /app/

# Expose the application's port 3000
EXPOSE 3000

# Run the application using the fat JAR
CMD java jar tf bff.jar | grep application.yaml
CMD java -jar /app/bff.jar
ENTRYPOINT ["java", "-jar", "/app/bff.jar"]
