# Lodgings Manager
You can manage your owned and rented lodgings in one place. You can add your lodgings and invite its tenants, or remove when she/he move out. Your tenants have the opportunity to report any to do about the lodgings.

## Screenshoots
![alt text](http://www.kepfeltoltes.eu/images/2019/03/648screencapture_localhos.png)
![alt text](http://www.kepfeltoltes.eu/images/2019/03/708screencapture_localhos.png)
![alt text](http://www.kepfeltoltes.eu/images/2019/03/685screencapture_localhos.png)

## Technology
```
Spring Boot
PostgresQL
```
## How to run
1. Make sure that you have this technology installed on your computer:
- Java 8
- Maven
- PostgreSQL

2. Open the pom.xml file in your code editor

3. You'll need to edit the application.properties file. (src/main/java/resources)
```java
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
spring.datasource.username=yourusename
spring.datasource.password=yourpass
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create-drop


# App Properties
airbnb.app.jwtSecret=yoursecretkey
airbnb.app.jwtExpiration=86400
```
4. Run the program!


