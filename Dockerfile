###### Build Spring Boot application using Gradle
FROM gradle:6.0.1-jdk8 AS build

# Copy source files to container
COPY --chown=gradle:gradle . /home/gradle/src

# Set work directory
WORKDIR /home/gradle/src

# Clean, build and generate the JAR (bootJar) using Gradle
RUN gradle clean build bootJar --no-daemon

# -----------------------------------------------------------

##### Run Spring Boot application
FROM openjdk:8-jre-slim

# Open the container's public port
EXPOSE 8080

# Create required folders (jar, logs, config)
RUN mkdir -p /opt/lpg/tourism_pass_management_system/jar
RUN mkdir -p /opt/lpg/tourism_pass_management_system/logs
RUN mkdir -p /opt/lpg/tourism_pass_management_system/config
RUN chown -R $(whoami) /opt/lpg

# Copy log config file to container
COPY src/main/resources/config/log4j2.xml /opt/lpg/tourism_pass_management_system/config/log4j2.xml

# Copy JAr file from previous container called 'build' to the container that will be used to run the app
COPY --from=build /home/gradle/src/build/libs/*.jar /opt/lpg/tourism_pass_management_system/jar/tourism_pass_management_system.jar

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "/opt/lpg/tourism_pass_management_system/jar/tourism_pass_management_system.jar"]
