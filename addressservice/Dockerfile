# İlk aşama: Maven ile derleme
FROM maven:3.8.3-openjdk-17 as build

# settings.xml dosyasını kopyalayın
COPY settings.xml /root/.m2/settings.xml

# Çalışma dizinini ayarla
WORKDIR /app

# Proje dosyalarını kopyala
COPY . /app/

# Maven ile projeyi derle
RUN mvn clean package -DskipTests

# İkinci aşama: OpenJDK ile çalıştırma
FROM openjdk:17-jdk

# Derlenen jar dosyasını kopyala
COPY --from=build /app/target/addressservice-2.0.0.jar /app/addressservice.jar

# İsteğe bağlı olarak uygulamanın çalışacağı portu açabilirsiniz
# EXPOSE 8082

# Uygulamayı çalıştır
CMD ["java", "-jar", "/app/addressservice.jar"]
