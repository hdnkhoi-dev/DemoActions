FROM maven:3.9.11-eclipse-temurin-17 AS builder
WORKDIR /workspace
COPY Ho_Nguyen_Dang_Khoi_DOCKER/ ./Ho_Nguyen_Dang_Khoi_DOCKER/
WORKDIR /workspace/Ho_Nguyen_Dang_Khoi_DOCKER
RUN mvn -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /workspace/Ho_Nguyen_Dang_Khoi_DOCKER/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
