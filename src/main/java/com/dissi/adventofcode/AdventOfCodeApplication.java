package com.dissi.adventofcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventOfCodeApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdventOfCodeApplication.class);
        app.setLogStartupInfo(false);
        app.setAdditionalProfiles("default");
        app.run(args);
    }
}
