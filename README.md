## Employee management system

## About

Web application is REST service for managing departments and their employees and users of this app.

### Technologies:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring Security](https://spring.io/projects/spring-security)
* [Spring Security OAuth 2.0](https://spring.io/projects/spring-security-oauth)
* [JPA](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)
* [MySQL](https://www.mysql.com/)
* [JUnit](https://junit.org/junit5/)
* [Mockito](https://site.mockito.org/)
* [Maven](https://maven.apache.org/)

### Usage

Clone repository:
```
https://github.com/Moro69/employee-management-system.git
```

Go into root project folder and run:
```
mvn clean package
```
 
Then put ```emp-manage.war``` from ```target``` to servlet container (e.x Tomcat, Jetty).

API will be available by address: 
```
/emp-manage/swagger-ui.html
```



