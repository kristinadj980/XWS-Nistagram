FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/eureka
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/eureka/target/eureka-0.0.1-SNAPSHOT.jar ./
EXPOSE 8761
CMD java -jar eureka-0.0.1-SNAPSHOT.jar