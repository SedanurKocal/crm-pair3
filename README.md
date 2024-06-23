# Turkcell Java 3 Bootcamp Final Projesi

## CRM Uygulaması

Bu proje, Turkcell Java 3 Bootcamp kapsamında verilen eğitim sonuncunda geliştirilen bir CRM (Customer Relationship Management) uygulamasıdır. 
Bu uygulama, müşteri ilişkilerini yönetmeyi ve iş süreçlerini optimize etmeyi amaçlayan çeşitli özellikler sunar. Bu uygulama pair3 ekibi tarafından geliştirilmiştir.

## İçindekiler
- [Özellikler](#özellikler)
- [Kullanılan Teknolojiler](#kullanılan-teknolojiler)
- [Kurulum](#kurulum)
- [Kullanım](#kullanım)
- [İletişim](#iletişim)


## Özellikler

- Müşteri bilgilerini ekleme, güncelleme ve silme
- Kategori ekleme, güncelleme ve silme
- Ürün ekleme, güncelleme ve silme
- Sepet oluşturma
- Satış takip etme
- Müşteri etkileşimlerini kaydetme ve yönetme
- Raporlama ve analiz 


  ## Kullanılan Teknolojiler

- Java
- Spring Boot
- Spring Cloud
- Spring Security
- Maven
- PostgreSQL
- MongoDB
- Docker
- Open API
- Postman

## Gereksinimler
- Java 11 veya üstü
- Maven 3.6.3 veya üstü
- PostgreSQL
- Docker
- MongoDB
## Kurulum

Projeyi yerel ortamınıza kurmak için aşağıdaki adımları takip edebilirsiniz:
### Manuel Kurulum

1. Bu repository'i klonlayın:
    ```bash
    git clone https://github.com/tcell-gygy-pair3/crm-pair3.git
    ```

2. Proje dizinine gidin:
    ```bash
    cd crm-pair3
    ```

3. Gerekli bağımlılıkları yükleyin:
    ```bash
    mvn install
    ```

4. Uygulamayı başlatın:
    ```bash
    mvn spring-boot:run
    ```
    
### Docker ile Kurulum (https://www.docker.com/products/docker-desktop/)

 1. Bu repository'i klonlayın:
    ```bash
    git clone https://github.com/tcell-gygy-pair3/crm-pair3.git
    ```

2. Proje dizinine gidin:
    ```bash
    cd crm-pair3
    ```
    
3. Docker bileşenlerini oluşturun ve başlatın:
    ```bash
    docker-compose up --build
    ```

    Docker, gerekli tüm bileşenleri (uygulama, veritabanı vb.) çalıştıracak ve uygulamayı `http://localhost:8080` adresinde erişilebilir hale getirecektir.

## Kullanım

Uygulamayı çalıştırdıktan sonra tarayıcınızda `http://localhost:8080` adresine giderek giriş yapabilirsiniz. 

## İletişim
Proje ekibi ile ilgili sorularınız veya önerileriniz için aşağıdaki kişilerle iletişime geçebilirsiniz:

- **Ahmet Melih İnce** - [LinkedIn](https://www.linkedin.com/in/melihince/) - [Github](https://github.com/melihnc)
- **Duygu Orhan** - [LinkedIn](https://www.linkedin.com/in/duygu-orhan-9a7a711ba/) - [Github](https://github.com/duygu2)
- **Oğuzhan Seçgel** - [LinkedIn](https://www.linkedin.com/in/oguzhansecgel) - [Github](https://github.com/oguzhansecgel)
- **Sedanur Koçal** - [LinkedIn](https://www.linkedin.com/in/sedanurkocal/) - [Github](https://github.com/SedanurKocal)
- **Yuşa Kaynar** - [LinkedIn](https://www.linkedin.com/in/yusa-kaynar/) - [Github](https://github.com/yusadev24)

Grup olarak birlikte çalışmaktan büyük keyif aldık ve bu projeyi geliştirdik. Her türlü geri bildirim ve katkıya açığız!

