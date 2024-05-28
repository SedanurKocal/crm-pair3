# İlk aşama: Maven ile derleme
FROM maven:3.8.3-openjdk-17 as build

# settings.xml dosyasını kopyalayın
COPY settings.xml /root/.m2/settings.xml

# Proje dosyalarını kopyala
COPY . /app

# Çalışma dizinini ayarla
WORKDIR /app

# Maven ile projeyi derle
RUN mvn clean package -DskipTests -e

# İkinci aşama: OpenJDK ile çalıştırma
FROM openjdk:17-jdk

# Derlenen jar dosyasını kopyala
COPY --from=build /app/target/customerservice-0.0.1-SNAPSHOT.jar /app/customerservice.jar

# Uygulamayı çalıştır
CMD ["java", "-jar", "/app/customerservice.jar"]
