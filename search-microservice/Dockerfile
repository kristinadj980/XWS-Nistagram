FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/search-microservice
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/search-microservice/target/search-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 8088
CMD java -jar search-microservice-0.0.1-SNAPSHOT.jar