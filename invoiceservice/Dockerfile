


FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests -e


FROM openjdk:17-jdk
COPY --from=build /app/target/invoiceservice-0.0.1-SNAPSHOT.jar /app/invoiceservice.jar
# EXPOSE 8082
CMD java -jar /app/invoiceservice.jar
