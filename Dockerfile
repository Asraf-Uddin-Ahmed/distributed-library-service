# Use the official Maven image as the build environment
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Use the official Tomcat image as the base image for the application
FROM tomcat:9.0.56-jdk17-openjdk-slim
WORKDIR /usr/local/tomcat/webapps

# Copy the built WAR file from the build environment to the Tomcat webapps directory
COPY --from=build /app/target/distributed-library-service-0.0.1-SNAPSHOT.war ./distributed-library-service.war

# Expose the port for the Tomcat server
EXPOSE 8080

# Start the Tomcat server and deploy the WAR file
CMD ["catalina.sh", "run"]
