# ====== Build Stage ======
# Use Maven with Java 21 to build the project
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only pom.xml first to cache dependencies
COPY pom.xml .
# If your project is in a subfolder, copy it accordingly
COPY src ./src

# Build the jar file, skip tests to speed up build
RUN mvn clean package -DskipTests

# ====== Run Stage ======
# Use lightweight JDK to run the jar
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render will map the correct $PORT)
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java","-jar","/app/app.jar"]
