FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/security-microservice
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/security-microservice/target/security-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 8090
CMD java -jar security-microservice-0.0.1-SNAPSHOT.jar