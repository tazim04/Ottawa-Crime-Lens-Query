# ---------- Build stage ----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy Maven wrapper & pom first (better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy source
COPY src src

# Build
RUN ./mvnw clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

ENV JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=25 -XX:MaxRAMPercentage=70 -XX:+UseSerialGC"

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
