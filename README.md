### Back-End

- Java 11
- SpringBoot 2.6.11
    - dependencies
        - Lombok
            - 'org.projectlombok:lombok'
        - JPA
            - 'org.springframework.boot:spring-boot-starter-data-jpa'
        - Spring Security: Login
            - 'org.springframework.boot:spring-boot-starter-security'
        - JWT
            - 'io.jsonwebtoken', name: 'jjwt', version: '0.9.1'
            - 'com.auth0', name: 'java-jwt', version: '3.19.0'
        - Chat
            - 'org.springframework.boot:spring-boot-starter-websocket'
            - 'org.webjars:stomp-websocket:2.3.3-1'
        - h2: Test Database
            - 'com.h2database:h2'