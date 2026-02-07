# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy pom first for better layer caching
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .

# Download dependencies (cache)
RUN ./mvnw -q -DskipTests dependency:go-offline

# Copy source and build
COPY src/ src/
RUN ./mvnw -q -DskipTests clean package

# ---------- Run stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar (works for typical Spring Boot Maven packaging)
COPY --from=build /workspace/target/*.jar /app/app.jar

# Run on 8080
EXPOSE 8080

# Good container defaults
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]