package fr.epsitek.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiDemoApplication {

    public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
    }
}
