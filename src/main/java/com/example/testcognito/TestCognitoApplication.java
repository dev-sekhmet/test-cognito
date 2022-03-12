package com.example.testcognito;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestCognitoApplication {
private final CognitoAdminClientService adminClientService;

    public TestCognitoApplication(CognitoAdminClientService adminClientService) {
        this.adminClientService = adminClientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestCognitoApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            adminClientService.adminCreateUser("juniortemate1@gmail.com", "TEMATE");
        };
    }
}
