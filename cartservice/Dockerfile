


FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests -e


FROM openjdk:17-jdk
COPY --from=build /app/target/cartservice-0.0.1-SNAPSHOT.jar /app/cartservice.jar
# EXPOSE 8082
CMD java -jar /app/cartservice.jar
