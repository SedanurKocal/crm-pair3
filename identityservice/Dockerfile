


FROM maven:3.8.3-openjdk-17 as build
COPY settings.xml /root/.m2/settings.xml
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests -e


FROM openjdk:17-jdk
COPY --from=build /app/target/identityservice-0.0.1-SNAPSHOT.jar /app/identityservice.jar
# EXPOSE 8082
CMD java -jar /app/identityservice.jar
