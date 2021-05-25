FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/searchMicroservice
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/searchMicroservice/target/demo-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
CMD java -jar demo-0.0.1-SNAPSHOT.jar