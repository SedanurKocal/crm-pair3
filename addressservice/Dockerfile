


FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk
COPY --from=build /app/target/addressservice-1.0.0.jar /app/addressservice.jar
# EXPOSE 8082
CMD ["java", "-jar", "/app/addressservice.jar"]
