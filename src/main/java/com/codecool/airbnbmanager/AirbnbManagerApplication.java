package com.codecool.airbnbmanager;

import com.codecool.airbnbmanager.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class AirbnbManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbnbManagerApplication.class, args);

    }
}
