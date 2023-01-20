package com.ots.trainingapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {FlywayAutoConfiguration.class})
public class TrainingApiApplication {
    
    public static void main(String[] args) {
        
        new SpringApplicationBuilder(TrainingApiApplication.class)
                .run(args);
    }
    
}
